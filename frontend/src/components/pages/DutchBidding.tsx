import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getItemById } from "../api/CatalogueApi";
import { placeBid } from "../api/AuctionApi";

const DutchBiddingPage = () => {
  const { itemId } = useParams<{ itemId: string }>();
  const [item, setItem] = useState<any | null>(null);
  const [error, setError] = useState<string | null>(null); // Error state

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

  const handleBuyNow = async () => {
    try {
      const userId = 1; // Replace with actual user ID
      const message = await placeBid(
        Number(itemId),
        item.item.itemPrice,
        userId
      );
      alert(message); // Show success message
      setItem({ ...item, winnerId: userId }); // Update winner info
    } catch (error: any) {
      setError(error.message); // Display error message
    }
  };

  if (!item) return <p>Loading item details...</p>;

  return (
    <div>
      <h1>Dutch Auction Bidding</h1>
      <Link to="/">Home</Link> <Link to="/catalogue">Catalogue</Link>
      <p>Item Name: {item.item.name}</p>
      <p>Item Description: {item.item.description}</p>
      <p>Current Price: ${item.item.itemPrice}</p>
      <p>
        Highest Bidder:{" "}
        {item.item.winnerId ? `User ${item.item.winnerId}` : "No bids yet"}
      </p>
      <button onClick={handleBuyNow}>Buy Now</button>
      {error && <p style={{ color: "red" }}>{error}</p>}{" "}
      {/* Display error message */}
    </div>
  );
};

export default DutchBiddingPage;
