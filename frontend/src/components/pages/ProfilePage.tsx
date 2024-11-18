import React from "react";

const ProfilePage: React.FC = () => {
  return (
    <div>
      <h2>User Profile</h2>
      <p>Username: johndoe</p>
      <p>Email: johndoe@example.com</p>
      <p>Auction History: </p>
      <ul>
        <li>Auction 1 - Won</li>
        <li>Auction 2 - Lost</li>
      </ul>
    </div>
  );
};

export default ProfilePage;
