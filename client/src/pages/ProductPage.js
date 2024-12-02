import React, { useState, useEffect } from "react";
import axios from "axios";
import ProductList from "../components/ProductList";
import ProductForm from "../components/ProductForm";

const API_URL = "http://localhost:8084/DemoAPI/api/products";

const ProductPage = () => {
  const [products, setProducts] = useState([]);
  const [editingProduct, setEditingProduct] = useState(null);

  // Fetch product list
  const fetchProducts = async () => {
    try {
      const response = await axios.get(API_URL);
      setProducts(response.data);
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  const handleSubmit = (product) => {
    if (product.id) {
      handleUpdate(product);
    } else {
      handleSave(product);
    }
  };

  // Create product
  const handleSave = async (newProduct) => {
    try {
      console.log('Saving new product:', newProduct);
      const response = await axios.post(API_URL, {
        name: newProduct.name,
        quantity: parseInt(newProduct.quantity)
      },
      {
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      });
      console.log('Product saved:', response.data);
      fetchProducts();
      setEditingProduct(null);
    } catch (error) {
      console.error('Error response data:', error.response);
      console.error('Error message:', error.message);
      console.error('Error saving product:', error);
    }
  };

  // Update product
  const handleUpdate = async (updatedProduct) => {
    try {
      console.log('Updating product:', updatedProduct);
      const response = await axios.put(`${API_URL}?id=${updatedProduct.id}&quantity=${updatedProduct.quantity}`, updatedProduct);
      console.log('Product updated:', response.data);
      fetchProducts(); 
      setEditingProduct(null);
    } catch (error) {
      console.error('Error updating product:', error);
    }
  };

  // Delete product
  const handleDeleteProduct = async (id) => {
    try {
      await axios.delete(`${API_URL}?id=${id}`);
      fetchProducts();
    } catch (error) {
      console.error("Error deleting product:", error);
    }
  };

  return (
    <div>
      <h1>Product Management</h1>
      <ProductList
        products={products}
        onEdit={(product) => setEditingProduct(product)}
        onDelete={handleDeleteProduct}
      />
      <ProductForm
        product={editingProduct}
        onSubmit={handleSubmit}
        onCancel={() => setEditingProduct(null)}
      />
    </div>
  );
};

export default ProductPage;
