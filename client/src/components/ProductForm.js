import React, { useState, useEffect } from "react";

const ProductForm = ({ product, onSubmit, onCancel }) => {
  const [formData, setFormData] = useState({ name: "", quantity: "" });

  useEffect(() => {
    if (product) {
      setFormData(product);
    }
  }, [product]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();
    onSubmit(formData);
    setFormData({ name: "", quantity: "" });
  };

  return (
    <div>
      <h2>{formData.id ? "Edit Product" : "Create Product"}</h2>
      <form onSubmit={handleFormSubmit}>
        <label>
          Name:
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Quantity:
          <input
            type="number"
            name="quantity"
            value={formData.quantity}
            onChange={handleChange}
            required
          />
        </label>
        <button type="submit">{formData.id ? "Update" : "Save"}</button>
        <button type="button" onClick={onCancel}>
          Cancel
        </button>
      </form>
    </div>
  );
};

export default ProductForm;
