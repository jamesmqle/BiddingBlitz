import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getItemById } from "../api/CatalogueApi";

const DutchBiddingPage = () => {
  const { itemId } = useParams<{ itemId: string }>();
  const [item, setItem] = useState<any | null>(null);

  useEffect(() => {
    // Ensure that itemId is available and is a valid number
    if (itemId) {
      const fetchItem = async () => {
        try {
          const item = await getItemById(Number(itemId));
          setItem(item); // Save item data to state
        } catch (error) {}
      };

      fetchItem();
    }
  }, [itemId]); // Add itemId as a dependency so the effect runs when itemId changes

  if (!item) return <p>Loading item details...</p>;

  return (
    <div>
      <h1>Dutch Auction Bidding</h1>
      <Link to="/">Home</Link> <Link to="/catalogue">Catalogue</Link>
      <p>Item Name: {item.item.name}</p>
      <p>Item Description: {item.item.description}</p>
      <p>Current Price: ${item.item.itemPrice}</p>
      <p>Highest Bidder: Temp Data</p>
      <button>Buy Now</button>
    </div>
  );
};

export default DutchBiddingPage;
