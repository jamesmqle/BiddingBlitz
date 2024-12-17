import React, { useEffect, useState } from "react";
import { useParams,useNavigate } from "react-router-dom";
import { getItemById } from "../api/CatalogueApi";
import UserDetailsFetcher, { UserDetails } from "../UserDetailsFetcher.tsx";

const PaymentPage: React.FC = () => {
  const { itemId } = useParams<{ itemId: string }>();
  const [item, setItem] = useState<any | null>(null);
  const [totalCost, setTotalCost] = useState<number>(0);
  const [userDetails, setUserDetails] = useState<null | {
    userInfo: any;
    userAddress: any;
  }>(null);
  const userId = localStorage.getItem("userId");
  const navigate = useNavigate();


  const [formData, setFormData] = useState({
    cardNumber: "",
    cardName: "",
    expirationDate: "",
    securityCode: "",
  });

  // Fetch item details
  useEffect(() => {
    if (itemId) {
      const fetchItem = async () => {
        try {
          const item = await getItemById(Number(itemId));
          setItem(item);
          setTotalCost(item.item.itemPrice + item.item.shippingPrice );
        } catch (error) {
          console.error("Failed to fetch item:", error);
        }
      };

      fetchItem();
    }
  }, [itemId]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
  
    for (const key in formData) {
      if (!formData[key as keyof typeof formData]) {
        alert("Please fill out all fields before submitting.");
        return;
      }
    }
  
    console.log("Payment submitted:", { ...formData, totalCost });
    alert("Payment successful!");
  
    navigate(`/receipt/${itemId}`);
  };

  return (
    <div>
      <h2>Payment Page</h2>
      <UserDetailsFetcher
        userId={userId} // Replace with dynamic user ID
        onUserDetailsFetched={setUserDetails}
      />
      <div> <p>
        First Name: {userDetails?.userInfo.firstName}
      </p>
      <p>
        Last Name: {userDetails?.userInfo.lastName}
      </p>
      <p>
        Street: {userDetails?.userAddress.streetAddress}
      </p>
      <p>
        Number: {userDetails?.userAddress.streetNumber}
      </p>
      <p>
        Postal Code: {userDetails?.userAddress.postalCode}
      </p>
      <p>
        City: {userDetails?.userAddress.city}
      </p>
      <p>
        Country: {userDetails?.userAddress.country}
      </p>

      </div>

      {item ? (
        <form onSubmit={handleSubmit}>
          <h3>Payment Information</h3>
          <input
            type="text"
            name="cardNumber"
            placeholder="Card Number"
            value={formData.cardNumber}
            onChange={handleChange}
            required
          />
          <input
            type="text"
            name="cardName"
            placeholder="Name on Card"
            value={formData.cardName}
            onChange={handleChange}
            required
          />
          <input
            type="text"
            name="expirationDate"
            placeholder="Expiration Date (MM/YY)"
            value={formData.expirationDate}
            onChange={handleChange}
            required
          />
          <input
            type="text"
            name="securityCode"
            placeholder="Security Code (CVV)"
            value={formData.securityCode}
            onChange={handleChange}
            required
          />

          <h3>Total Cost: ${totalCost.toFixed(2)}</h3>
          <button type="submit">Complete Payment</button>
        </form>
      ) : (
        <p>Loading item details...</p>
      )}
    </div>
  );
};

export default PaymentPage;