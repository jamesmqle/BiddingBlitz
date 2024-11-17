import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getItemById } from "../api/CatalogueApi";
import { placeBid } from "../api/AuctionApi"; // Import the placeBid function

const ForwardBiddingPage = () => {
  const { itemId } = useParams<{ itemId: string }>();
  const [item, setItem] = useState<any | null>(null);
  const [bidAmount, setBidAmount] = useState<number | "">(""); // Bid amount state
  const [error, setError] = useState<string | null>(null); // Error state
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

  const handleBid = async () => {
    if (!bidAmount || bidAmount <= 0) {
      setError("Please enter a valid bid amount.");
      return;
    }

    try {
      await placeBid(Number(itemId), bidAmount, Number(userId));
      setItem(await getItemById(Number(itemId))); // Update item price in UI
      setBidAmount(""); // Reset bid input field
    } catch (error: any) {
      setError(error.message); // Display error message
    }
  };

  if (!item) return <p>Loading item details...</p>;

  return (
    <div>
      <h1>Forward Auction Bidding</h1>
      <Link to="/">Home</Link> <Link to="/catalogue">Catalogue</Link>
      <p>Item Name: {item.item.name}</p>
      <p>Item Description: {item.item.description}</p>
      <p>Current Price: ${item.item.itemPrice}</p>
      <p>
        Highest Bidder:{" "}
        {item.item.winnerId ? `User ${item.item.winnerId}` : "No bids yet"}
      </p>
      <input
        type="number"
        value={bidAmount}
        onChange={(e) => setBidAmount(Number(e.target.value))}
        placeholder="Enter your bid"
      />
      <button onClick={handleBid}>Place Bid</button>
      {error && <p style={{ color: "red" }}>{error}</p>}{" "}
      {/* Display error message */}
    </div>
  );
};

export default ForwardBiddingPage;
