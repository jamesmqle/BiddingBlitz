import { useState, useEffect } from "react";
import { getUserDetails } from "./api/UserApi";

interface UserInfo {
  userId: number;
  firstName: string;
  lastName: string;
  email: string;
}

interface UserAddress {
  streetNumber: number;
  streetAddress: string;
  city: string;
  postalCode: string;
  country: string;
}

interface UserDetails {
  userInfo: UserInfo;
  userAddress: UserAddress;
}

interface UserDetailsFetcherProps {
  userId: string | null;
  onUserDetailsFetched: (userDetails: UserDetails | null) => void; // Callback to send the data back
}

const UserDetailsFetcher: React.FC<UserDetailsFetcherProps> = ({
  userId,
  onUserDetailsFetched,
}) => {
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (userId) {
      setLoading(true); // Start loading state
      setError(null); // Reset error state

      getUserDetails(userId)
        .then((response: { data: UserDetails | null }) => {
          onUserDetailsFetched(response.data);
        })
        .catch(() => {
          const errorMessage = "Error fetching user details";
          setError(errorMessage);
          onUserDetailsFetched(null); // Send error to parent
        })
        .finally(() => {
          setLoading(false); // End loading state
        });
    }
  }, []);

  // Show loading and error states
  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return null; // Return null as this component is just for fetching and sending data
};

export default UserDetailsFetcher;
