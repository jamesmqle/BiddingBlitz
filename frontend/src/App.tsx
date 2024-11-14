import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./components/pages/HomePage";
import PaymentPage from "./components/pages/PaymentPage";
import ProfilePage from "./components/pages/ProfilePage";
import ReceiptPage from "./components/pages/ReceiptPage";
import SignInPage from "./components/pages/SignInPage";
import SignUpPage from "./components/pages/SignUpPage";
import Catalogue from "./components/pages/Catalogue";

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/payment" element={<PaymentPage />} />
        <Route path="/profile" element={<ProfilePage />} />
        <Route path="/receipt" element={<ReceiptPage />} />
        <Route path="/signin" element={<SignInPage />} />
        <Route path="/signup" element={<SignUpPage />} />
        <Route path="/catalogue" element={<Catalogue />} />
      </Routes>
    </Router>
  );
};

export default App;
