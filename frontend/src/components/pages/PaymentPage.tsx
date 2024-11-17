import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getItemById } from "../api/CatalogueApi";

const PaymentPage: React.FC = () => {
  const { itemId } = useParams<{ itemId: string }>();
  const [item, setItem] = useState<any | null>(null);

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

  };

  return (
    <div>
      <h2>Payment Page</h2>
        <button type="submit">Complete Payment</button>
    </div>
  );
};

export default PaymentPage;
