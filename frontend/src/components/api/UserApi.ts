// UserApi.ts
const API_BASE_URL = 'http://localhost:8080/api/users'; // Replace with your backend URL

// Register a new user
export const registerUser = async (user: { username: string; password: string; email: string }): Promise<any> => {
  try {
    const response = await fetch(`${API_BASE_URL}/register`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(user),
    });
    if (!response.ok) throw new Error('Failed to register user');
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    return null;
  }
};

// Login a user
export const loginUser = async (credentials: { username: string; password: string }): Promise<any> => {
  try {
    const response = await fetch(`${API_BASE_URL}/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(credentials),
    });
    if (!response.ok) throw new Error('Failed to log in');
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    return null;
  }
};

// Update user profile
export const updateUser = async (userId: string, user: { email: string; password?: string }): Promise<any> => {
  try {
    const response = await fetch(`${API_BASE_URL}/${userId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(user),
    });
    if (!response.ok) throw new Error(`Failed to update user with ID: ${userId}`);
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    return null;
  }
};

// Delete user account
export const deleteUser = async (userId: string): Promise<void> => {
  try {
    const response = await fetch(`${API_BASE_URL}/${userId}`, { method: 'DELETE' });
    if (!response.ok) throw new Error(`Failed to delete user with ID: ${userId}`);
  } catch (error) {
    console.error('Error:', error);
  }
};
