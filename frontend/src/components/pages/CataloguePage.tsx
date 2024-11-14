import React from "react";
import { Link } from "react-router-dom";

const Catalogue: React.FC = () => {
  return (
    <div>
      <h2>Catalogue</h2>
      <ul>
        <li>
          <Link to="/auction/1">Item 1 - Auction</Link>
        </li>
        <li>
          <Link to="/auction/2">Item 2 - Auction</Link>
        </li>
        <li>
          <Link to="/auction/3">Item 3 - Auction</Link>
        </li>
      </ul>
    </div>
  );
};

export default Catalogue;
