import React, { useEffect, useState } from "react";
import { getItemById, getItemDetails } from "../api/CatalogueApi";
import { Link } from "react-router-dom";

const CataloguePage = () => {
  const [items, setItems] = useState<any[]>([]);
  const [searchKeyword, setSearchKeyword] = useState<string>("");
  const [selectedItemId, setSelectedItemId] = useState<number | null>(null);
  const [selectedItem, setSelectedItem] = useState<any | null>(null);
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

  // Handle radio button change
  const handleSelectChange = async (itemId: number) => {
    setSelectedItemId(itemId);
    try {
      const item = await getItemById(itemId);
      setSelectedItem(item);
    } catch {
      setError("Failed to fetch selected item details.");
    }
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
                <th>Select</th>
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
                  <td>
                    <input
                      type="radio"
                      name="selectItem"
                      value={item.itemId}
                      checked={selectedItemId === item.itemId}
                      onChange={() => handleSelectChange(item.itemId)}
                    />
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p>No items found</p>
        )}
      </div>

      {selectedItem && (
        <div>
          {selectedItem.forwardAuction ? (
            <Link to={`/forward-bidding/${selectedItemId}`}>
              <button>Bid</button>
            </Link>
          ) : (
            <Link to={`/dutch-bidding/${selectedItemId}`}>
              <button>Bid</button>
            </Link>
          )}
        </div>
      )}
    </div>
  );
};

export default CataloguePage;
