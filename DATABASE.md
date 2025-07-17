# 🏠 Room Rental Platform - Database Schema Documentation

This document outlines the database schema for the Room Rental Platform, including the purpose of each table, key fields, and relationships.

---

## 📋 Table Overview

| Table Name        | Description                                                  |
|-------------------|--------------------------------------------------------------|
| `roomOwners`      | Stores information about users who list rooms for rent       |
| `roomSeekers`     | Stores information about users who are looking for rooms     |
| `rooms`           | Contains the actual room listings with all details           |
| `bookings`        | Tracks booking details between seekers and rooms             |
| `reviews`         | Contains user reviews and ratings for rooms                  |
| `messages`        | Direct messages exchanged between owners and seekers         |
| `payments`        | Records payment transactions for bookings                    |
| `admins`          | Admin users for managing and moderating the platform         |
| `favorites`       | Seekers’ bookmarked/favorite rooms                           |
| `notifications`   | Platform notifications for seekers and owners                |
| `room_images`     | Stores multiple images for each room listing                 |
| `amenities`       | Master table of available amenities                          |
| `room_amenities`  | Maps amenities to rooms (many-to-many relationship)          |

---

## 🧾 Table Descriptions

### 1. `roomOwners`

Stores information about room owners.

| Field         | Type      | Description                        |
|---------------|-----------|------------------------------------|
| `id`          | PK        | Unique identifier                  |
| `name`        | string    | Full name                          |
| `email`       | string    | Email address                      |
| `phone`       | string    | Contact number                     |
| `address`     | string    | Residential or business address    |
| `verified`    | boolean   | Whether the profile is verified    |
| `rating`      | decimal   | Average rating                     |
| `reviewCount` | int       | Total number of reviews            |

---

### 2. `roomSeekers`

Stores information about users looking for rooms.

| Field            | Type      | Description                            |
|------------------|-----------|----------------------------------------|
| `id`             | PK        | Unique identifier                      |
| `name`           | string    | Full name                              |
| `email`          | string    | Email address                          |
| `phone`          | string    | Contact number                         |
| `age`            | int       | Age of the seeker                      |
| `occupation`     | string    | Job title or student                   |
| `preferences`    | JSON      | Room preferences                       |
| `verified`       | boolean   | Whether the profile is verified        |

---

### 3. `rooms`

Contains details about listed rooms.

| Field               | Type      | Description                              |
|---------------------|-----------|------------------------------------------|
| `id`                | PK        | Unique identifier                        |
| `ownerId`           | FK        | References `roomOwners.id`               |
| `title`             | string    | Room listing title                       |
| `description`       | text      | Full description                         |
| `price`             | decimal   | Monthly rent                             |
| `location`          | string    | Area or locality                         |
| `district`          | string    | Administrative district                  |
| `distanceFromCenter`| decimal   | Distance from city center (in km)        |
| `roomType`          | enum      | studio / shared / private / single       |
| `imageUrl`          | string    | Primary image URL                        |
| `amenities`         | JSON      | List of amenities (if not normalized)    |
| `rating`            | decimal   | Avg rating                               |
| `reviewCount`       | int       | Number of reviews                        |
| `isAvailable`       | boolean   | Availability status                      |

---

### 4. `bookings`

Tracks room bookings.

| Field        | Type      | Description                          |
|--------------|-----------|--------------------------------------|
| `id`         | PK        | Unique booking ID                    |
| `roomId`     | FK        | References `rooms.id`                |
| `seekerId`   | FK        | References `roomSeekers.id`          |
| `startDate`  | date      | Start of booking                     |
| `endDate`    | date      | End of booking                       |
| `status`     | enum      | pending / confirmed / cancelled      |
| `createdAt`  | timestamp | Booking creation date                |

---

### 5. `reviews`

Stores reviews on rooms.

| Field      | Type      | Description                         |
|------------|-----------|-------------------------------------|
| `id`       | PK        | Unique review ID                    |
| `seekerId` | FK        | Reviewer (from `roomSeekers`)       |
| `roomId`   | FK        | Reviewed room                       |
| `rating`   | decimal   | Rating value                        |
| `comment`  | text      | Review comment                      |
| `createdAt`| timestamp | Review creation date                |

---

### 6. `messages`

Chat system between seekers and owners.

| Field      | Type      | Description                        |
|------------|-----------|------------------------------------|
| `id`       | PK        | Unique message ID                  |
| `fromId`   | FK        | Sender (owner or seeker ID)        |
| `toId`     | FK        | Receiver                           |
| `message`  | text      | Message content                    |
| `sentAt`   | timestamp | Time the message was sent          |

---

### 7. `payments`

Payment tracking for bookings.

| Field       | Type      | Description                         |
|-------------|-----------|-------------------------------------|
| `id`        | PK        | Unique payment ID                   |
| `bookingId` | FK        | References `bookings.id`            |
| `amount`    | decimal   | Payment amount                      |
| `method`    | string    | UPI, card, bank transfer, etc.      |
| `status`    | enum      | paid / failed / pending             |
| `paidAt`    | timestamp | Payment completion time             |

---

### 8. `admins`

Admin panel users.

| Field     | Type    | Description                  |
|-----------|---------|------------------------------|
| `id`      | PK      | Unique admin ID              |
| `email`   | string  | Login email                  |
| `passwordHash` | string | Hashed password           |
| `role`    | enum    | moderator / superadmin       |

---

### 9. `favorites`

Saved rooms by seekers.

| Field      | Type    | Description                  |
|------------|---------|------------------------------|
| `id`       | PK      | Unique ID                    |
| `seekerId` | FK      | References `roomSeekers.id`  |
| `roomId`   | FK      | References `rooms.id`        |

---

### 10. `notifications`

User alerts.

| Field      | Type      | Description                        |
|------------|-----------|------------------------------------|
| `id`       | PK        | Unique notification ID             |
| `userId`   | FK        | Owner or seeker ID                 |
| `message`  | string    | Notification message               |
| `type`     | string    | new-message / booking-confirmed    |
| `isRead`   | boolean   | Read/unread flag                   |
| `createdAt`| timestamp | Time of notification               |

---

### 11. `room_images`

Stores multiple room photos.

| Field    | Type    | Description                    |
|----------|---------|--------------------------------|
| `id`     | PK      | Unique image ID                |
| `roomId` | FK      | References `rooms.id`          |
| `url`    | string  | Image URL                      |
| `caption`| string  | Optional description            |

---

### 12. `amenities`

Master list of amenities.

| Field    | Type    | Description         |
|----------|---------|---------------------|
| `id`     | PK      | Amenity ID          |
| `name`   | string  | e.g., wifi, parking |

---

### 13. `room_amenities`

Maps rooms to amenities (many-to-many).

| Field     | Type    | Description                 |
|-----------|---------|-----------------------------|
| `id`      | PK      | Unique ID                   |
| `roomId`  | FK      | References `rooms.id`       |
| `amenityId`| FK     | References `amenities.id`   |

---

## ✅ Relationships Summary

- `roomOwners` → `rooms` (1-to-many)
- `roomSeekers` → `bookings` (1-to-many)
- `rooms` → `bookings`, `reviews`, `room_images`, `room_amenities`
- `roomSeekers` ↔ `messages` ↔ `roomOwners`
- `bookings` → `payments`
- `amenities` ↔ `rooms` (many-to-many via `room_amenities`)

---

## 📌 Notes

- Use indexes on foreign keys for performance.
- Use constraints to maintain data integrity.
- For sensitive data like `passwordHash`, always use secure hashing (bcrypt, argon2, etc.).

---

> This schema provides a strong foundation for building a scalable and feature-rich room rental application.

