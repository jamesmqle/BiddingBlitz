import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getItemById } from "../api/CatalogueApi";

const AuctionEndedPage: React.FC = () => {
  const { itemId } = useParams<{ itemId: string }>();
  const [isExpeditedShipping, setExpeditedShipping] = useState(false);
  const [item, setItem] = useState<any | null>(null);
  const userId = localStorage.getItem("userId");

  useEffect(() => {
    if (itemId) {
      const fetchItem = async () => {
        try {
          const item = await getItemById(Number(itemId));
          setItem(item);
        } catch (error) {
          console.error(error);
        }
      };

      fetchItem();
    }
  }, [itemId]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Add any necessary handling when the form is submitted
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

      <p>Highest Bidder: {item.item.winnerId != null ? `User ${item.item.winnerId}` : "null"}</p>
      <button type="submit">Pay Now</button>
    </div>
  );
};

export default AuctionEndedPage;
