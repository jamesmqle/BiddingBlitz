# BiddingBlitz

## Overview

Bidding Blitz is a web-based e-commerce application that facilitates auctions using Forward and Dutch Auction models. It allows users to bid on items, make payments, and manage transactions seamlessly.

---

## Features

- **Forward Auctions**: Traditional auction system where the highest bidder wins.
- **Dutch Auctions**: Auction system where prices decrease over time.
- **Payment Integration**: Users can process payments for auction items.
- **Transaction History**: View payment and shipping details for completed transactions.

---

## Prerequisites

- **Backend**: Java (Spring Boot)
- **Frontend**: Node.js (React)
- **Database**: SQLite

Ensure that the following tools are installed:
- Java 11 or higher
- Node.js and npm
- SQLite

---

## How to Run

1. **Set up the backend**:  
Navigate to the backend directory and run the main application:
```bash
   cd backend
   run com.example.backend.BiddingBlitzApplication.java
```

2. **Set up the frontend**:
Navigate to the frontend directory and start the development server:
```bash
  cd frontend
  npm run dev
```

3. **Populate the Databases**:
Before running the application, populate the user and item databases using the provided SQL scripts found in:
```bash
  backend/src/main/resources/auction-database.sql
  backend/src/main/resources/user-database.sql
```
execute these scripts in your SQLite database to load the required data
