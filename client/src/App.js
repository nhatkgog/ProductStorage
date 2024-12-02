import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ProductPage from './pages/ProductPage';

const App = () => (
  <Router>
    <Routes>
      <Route path="/" element={<ProductPage />} />
    </Routes>
  </Router>
);

export default App;