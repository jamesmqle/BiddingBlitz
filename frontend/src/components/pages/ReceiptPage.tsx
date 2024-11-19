import React, { useState, useEffect } from "react";
import { useParams,useNavigate } from "react-router-dom";
import { getItemById } from "../api/CatalogueApi";
import { getPaidInfo } from "../api/PaymentApi";
import UserDetailsFetcher, { UserDetails } from "../UserDetailsFetcher.tsx";

const ReceiptPage: React.FC = () => {
  const { itemId } = useParams<{ itemId: string }>();
  const [item, setItem] = useState<any | null>(null);
  const [userDetails, setUserDetails] = useState<null | {
    userInfo: any;
    userAddress: any;
  }>(null);
  const [transactionDetails, setTransactionDetails] = useState<any | null>(null); // State for payment details
  const userId = localStorage.getItem("userId");
  const navigate = useNavigate(); // Initialize the useNavigate hook

  // Fetch item details
  useEffect(() => {
    if (itemId) {
      const fetchItem = async () => {
        try {
          const fetchedItem = await getItemById(Number(itemId));
          setItem(fetchedItem);
        } catch (error) {
          console.error("Failed to fetch item details:", error);
        }
      };
  
      fetchItem();
    }
  }, [itemId]);

    // Fetch transaction details
    useEffect(() => {
      if (itemId) {
        const fetchTransactionDetails = async () => {
          try {
            const paymentDetails = await getPaidInfo(Number(itemId));
            setTransactionDetails(paymentDetails);
          } catch (error) {
            console.error("Failed to fetch transaction details:", error);
          }
        };
  
        fetchTransactionDetails();
      }
    }, [itemId]);

  return (
    <div>
      <h1>Receipt</h1>
      <p>Thank you for your purchase!</p>
      <UserDetailsFetcher
        userId={userId}
        onUserDetailsFetched={setUserDetails}
      />

      <div>
      <h3>User Details</h3>
        <p><strong>First Name:</strong> {userDetails?.userInfo.firstName}</p>
        <p><strong>Last Name:</strong> {userDetails?.userInfo.lastName}</p>
        <p><strong>Street:</strong> {userDetails?.userAddress.streetAddress}</p>
        <p><strong>Street Number:</strong> {userDetails?.userAddress.streetNumber}</p>
        <p><strong>Postal Code:</strong> {userDetails?.userAddress.postalCode}</p>
        <p><strong>City:</strong> {userDetails?.userAddress.city}</p>
        <p><strong>Country:</strong> {userDetails?.userAddress.country}</p>
      </div>

      
      <div>
      <h3>Item Details</h3>
        {/* <p><strong>Item ID:</strong> {item.item.itemId}</p>
        <p><strong>Total Amount:</strong> ${item.item.itemPrice + item.item.shippingPrice}</p> */}
        </div>

        <div>
        <h3>Shipping Details</h3>

        <p><strong>Shipping Method:</strong> item</p>
        <p><strong>Estimated Delivery:</strong> !!! days</p>
      </div>

      <button onClick={() => navigate("/")}>Go to Homepage</button>
    </div>
  );
};

export default ReceiptPage;