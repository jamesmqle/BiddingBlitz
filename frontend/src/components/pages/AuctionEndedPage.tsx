import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getItemById } from "../api/CatalogueApi";

const AuctionEndedPage: React.FC = () => {
  const { itemId } = useParams<{ itemId: string }>();
  const navigate = useNavigate(); // Hook for navigation
  const [isExpeditedShipping, setExpeditedShipping] = useState(false);
  const [item, setItem] = useState<any | null>(null);
  const [error, setError] = useState<string | null>(null); // Error state
  const userId = localStorage.getItem("userId");

  useEffect(() => {
    if (itemId) {
      const fetchItem = async () => {
        try {
          const fetchedItem = await getItemById(Number(itemId));
          setItem(fetchedItem);
        } catch (error) {
          console.error(error);
        }
      };

      fetchItem();
    }
  }, [itemId]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    setError(null); // Clear previous errors

    if (!item) return;

    if (item.item.winnerId === Number(userId)) {
      // Navigate to payment page
      navigate(`/payment/${itemId}`, { state: { expedited: isExpeditedShipping } });
    } else {
      // Display error message
      setError("You are not the winner of this auction. Payment is not allowed.");
    }
  };

  if (!item) return <p>Loading item details...</p>;

  return (
    <div>
      <h2>Bidding Ended!</h2>
      <p>Item Name: {item.item.name}</p>
      <p>Item Description: {item.item.description}</p>
      <p>Winning Price: {item.item.itemPrice}</p>
      <p>Shipping Price: ${item.item.shippingPrice}</p>

      {/* Radio buttons for expedited shipping */}
      <div>
        <label>
          <input
            type="radio"
            name="shippingOption"
            value="expedited"
            checked={isExpeditedShipping}
            onChange={() => setExpeditedShipping(true)}
          />
          Expedited Shipping
        </label>
        <label>
          <input
            type="radio"
            name="shippingOption"
            value="standard"
            checked={!isExpeditedShipping}
            onChange={() => setExpeditedShipping(false)}
          />
          Standard Shipping
        </label>
      </div>

      <p>Highest Bidder: {item.item.winnerId != null ? `User ${item.item.winnerId}` : "No bids"}</p>

      {/* Error message display */}
      {error && <p style={{ color: "red" }}>{error}</p>}

      <button onClick={handleSubmit}>Pay Now</button>
    </div>
  );
};

export default AuctionEndedPage;
