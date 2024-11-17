// src/pages/CataloguePage.tsx

import React, { useEffect, useState } from "react";
import { getItemDetails } from "../api/CatalogueApi";
import { Link } from "react-router-dom";

const CataloguePage = () => {
  const [items, setItems] = useState<any[]>([]);
  const [searchKeyword, setSearchKeyword] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string>("");

  // Fetch items based on search keyword
  const fetchItems = async (keyword: string | null) => {
    setLoading(true);
    setError("");
    try {
      const data = await getItemDetails(keyword);
      setItems(data);
    } catch (error) {
      setError("Failed to load items.");
    } finally {
      setLoading(false);
    }
  };

  // Handle search submit (to trigger the API call)
  const handleSearchSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    fetchItems(searchKeyword || null); // Pass null for no keyword
  };

  // Effect to fetch items when component is mounted or when search keyword changes
  useEffect(() => {
    fetchItems(null); // Initially fetch all items on mount
  }, []);

  return (
    <div>
      <h1>Catalogue</h1>
      <Link to="/">Home</Link>

      <form onSubmit={handleSearchSubmit}>
        <input
          type="text"
          value={searchKeyword}
          onChange={(e) => setSearchKeyword(e.target.value)}
          placeholder="Search for items"
        />
        <button type="submit">Search</button>
      </form>

      {loading && <p>Loading items...</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}

      <div>
        {items.length > 0 ? (
          <table>
            <thead>
              <tr>
                <th>Item Name</th>
                <th>Price</th>
                <th>Auction Type</th>
                <th>Remaining Time</th>
              </tr>
            </thead>
            <tbody>
              {items.map(({ item, forwardAuction }) => (
                <tr key={item.itemId}>
                  <td>{item.name}</td>
                  <td>${item.itemPrice}</td>
                  <td>{item.auctionType}</td>
                  {forwardAuction ? (
                    <td>{forwardAuction.remainingTime} seconds</td>
                  ) : (
                    <td>Now</td>
                  )}
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p>No items found</p>
        )}
      </div>
    </div>
  );
};

export default CataloguePage;
