import React from "react";
import { Link } from "react-router-dom";

const HomePage: React.FC = () => {
  return (
    <div>
      <h1>Welcome to BiddingBlitz</h1>
      <p>Your one-stop auction site for amazing items!</p>
      <Link to="/signin">Sign In</Link> | <Link to="/signup">Sign Up</Link>
      <br />
      <Link to="/catalogue">Browse Items</Link>
    </div>
  );
};

export default HomePage;
