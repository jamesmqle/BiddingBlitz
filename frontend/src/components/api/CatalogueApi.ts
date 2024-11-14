// CatalogueApi.ts
const API_BASE_URL = 'http://localhost:8080/api/items'; // Replace with your backend URL

// Fetch all items
export const getItems = async (): Promise<any[]> => {
  try {
    const response = await fetch(`${API_BASE_URL}`);
    if (!response.ok) throw new Error('Failed to fetch items');
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    return [];
  }
};

// Fetch a specific item by ID
export const getItemById = async (itemId: string): Promise<any> => {
  try {
    const response = await fetch(`${API_BASE_URL}/${itemId}`);
    if (!response.ok) throw new Error(`Failed to fetch item with ID: ${itemId}`);
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    return null;
  }
};

// Create a new item
export const createItem = async (item: { name: string; price: number; description: string }): Promise<any> => {
  try {
    const response = await fetch(`${API_BASE_URL}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(item),
    });
    if (!response.ok) throw new Error('Failed to create item');
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    return null;
  }
};

// Update an existing item
export const updateItem = async (itemId: string, item: { name: string; price: number; description: string }): Promise<any> => {
  try {
    const response = await fetch(`${API_BASE_URL}/${itemId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(item),
    });
    if (!response.ok) throw new Error(`Failed to update item with ID: ${itemId}`);
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    return null;
  }
};

// Delete an item
export const deleteItem = async (itemId: string): Promise<void> => {
  try {
    const response = await fetch(`${API_BASE_URL}/${itemId}`, { method: 'DELETE' });
    if (!response.ok) throw new Error(`Failed to delete item with ID: ${itemId}`);
  } catch (error) {
    console.error('Error:', error);
  }
};
