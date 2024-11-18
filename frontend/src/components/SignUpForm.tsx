import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUser } from "./api/UserApi";

const SignUpForm: React.FC = () => {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    username: "",
    password: "",
    streetNumber: "",
    streetAddress: "",
    postalCode: "",
    city: "",
    country: "",
  });

  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      await registerUser({
        userInfo: {
          firstName: formData.firstName,
          lastName: formData.lastName,
        },
        userCredentials: {
          username: formData.username,
          password: formData.password,
        },
        userAddress: {
          streetNumber: formData.streetNumber,
          streetAddress: formData.streetAddress,
          postalCode: formData.postalCode,
          city: formData.city,
          country: formData.country,
        },
      });
      setMessage("Registration successful!");

      // Navigate to the login page
      navigate("/signin");
    } catch (error: any) {
      setMessage(
        "Registration failed: " + (error.response?.data || error.message)
      );
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Register</h2>
      <div>
        <input
          name="firstName"
          placeholder="First Name"
          onChange={handleChange}
        />
        <input
          name="lastName"
          placeholder="Last Name"
          onChange={handleChange}
        />
      </div>
      <div>
        <input name="username" placeholder="Username" onChange={handleChange} />
        <input
          name="password"
          type="password"
          placeholder="Password"
          onChange={handleChange}
        />
      </div>
      <div>
        <input
          name="streetNumber"
          placeholder="Street Number"
          onChange={handleChange}
        />
        <input
          name="streetAddress"
          placeholder="Street Address"
          onChange={handleChange}
        />
        <input
          name="postalCode"
          placeholder="Postal Code"
          onChange={handleChange}
        />
        <input name="city" placeholder="City" onChange={handleChange} />
        <input name="country" placeholder="Country" onChange={handleChange} />
      </div>
      <button type="submit">Register</button>
      <p>{message}</p>
    </form>
  );
};

export default SignUpForm;
