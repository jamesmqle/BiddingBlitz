import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./components/pages/HomePage";
import PaymentPage from "./components/pages/PaymentPage";
import ReceiptPage from "./components/pages/ReceiptPage";
import SignInPage from "./components/pages/SignInPage";
import SignUpPage from "./components/pages/SignUpPage";
import Catalogue from "./components/pages/CataloguePage";
import ForwardBiddingPage from "./components/pages/ForwardBidding";
import DutchBiddingPage from "./components/pages/DutchBidding";
import AuctionEndedPage from "./components/pages/AuctionEndedPage";

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/signin" element={<SignInPage />} />
        <Route path="/signup" element={<SignUpPage />} />
        <Route path="/catalogue" element={<Catalogue />} />
        <Route
          path="/forward-bidding/:itemId"
          element={<ForwardBiddingPage />}
        />
        <Route path="/dutch-bidding/:itemId" element={<DutchBiddingPage />} />
        <Route path="/auction-end/:itemId" element={<AuctionEndedPage />} />
        <Route path="/payment/:itemId" element={<PaymentPage />} />
        <Route path="/receipt/:itemId" element={<ReceiptPage />} />
      </Routes>
    </Router>
  );
};

export default App;
