import React, { useState } from "react";
import { Link } from "react-router-dom";
import UserDetailsFetcher from "../UserDetailsFetcher";

const HomePage: React.FC = () => {
  const [userDetails, setUserDetails] = useState<null | {
    userInfo: any;
    userAddress: any;
  }>(null); // Store the user details here
  const userId = localStorage.getItem("userId");

  // Callback to set the fetched userDetails
  const handleUserDetailsFetched = (fetchedUserDetails: any) => {
    setUserDetails(fetchedUserDetails); // Store the fetched data in state
  };

  return (
    <div>
      <h1>Welcome to BiddingBlitz</h1>
      {userId ? (
        <>
          <UserDetailsFetcher
            userId={userId}
            onUserDetailsFetched={handleUserDetailsFetched}
          />
          {userDetails && (
            <div>
              <h2>
                Welcome, {userDetails.userInfo.firstName}{" "}
                {userDetails.userInfo.lastName}
              </h2>
              <p>
                Street Address: {userDetails.userAddress.streetAddress},{" "}
                {userDetails.userAddress.city}
              </p>
              <p>
                Postal Code: {userDetails.userAddress.postalCode},{" "}
                {userDetails.userAddress.country}
              </p>
            </div>
          )}
        </>
      ) : (
        <>
          <p>Your one-stop auction site for amazing items!</p>
          <Link to="/signin">Sign In</Link> | <Link to="/signup">Sign Up</Link>
        </>
      )}
    </div>
  );
};

export default HomePage;
