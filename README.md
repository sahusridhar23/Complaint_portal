# Silicon University — Complaint Management Portal

A lightweight web application that allows students and staff of Silicon University to submit and view complaints across various campus categories. Built with Java Servlets, JDBC, and a plain HTML/CSS/JS frontend.

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Database Setup](#database-setup)
- [DB Credentials Configuration](#db-credentials-configuration)
- [Running the Project](#running-the-project)
- [API Endpoints](#api-endpoints)
- [Dependencies (JARs)](#dependencies-jars)

---

## Features

- Submit a complaint with your name, category, title, and description
- View all submitted complaints on a live dashboard
- Complaint categories: Classroom, Hostel, Library, Cafeteria, IT/Network, Other
- Status badges (Pending, In Progress, Resolved) on complaint cards
- Clean responsive UI with Silicon University branding
- REST-style JSON API backed by Java Servlets

---

## Tech Stack

| Layer       | Technology                      |
|-------------|---------------------------------|
| Backend     | Java Servlets (Jakarta EE)      |
| Database    | PostgreSQL                      |
| JDBC Driver | postgresql-42.7.10.jar          |
| JSON        | Google Gson 2.10.1              |
| Frontend    | HTML5, CSS3, Vanilla JavaScript |
| Server      | Apache Tomcat 10+ (Jakarta EE)  |

---

## Prerequisites

Ensure the following are installed before running the project:

| Tool           | Version / Notes                                                      |
|----------------|----------------------------------------------------------------------|
| Java (JDK)     | Java 17 or higher (uses the `record` keyword)                        |
| Apache Tomcat  | Version 10 or higher (required for the `jakarta.*` namespace)        |
| PostgreSQL     | Version 13 or higher                                                 |
| Browser        | Any modern browser (Chrome, Firefox, Edge)                           |

> **Important:** Do not use Tomcat 9 or lower. This project uses `jakarta.servlet.*` imports, which are only available from Tomcat 10 onwards.

---

## Project Structure

```
Complaint_portal-main/
│
├── index.html                   # Dashboard — lists all complaints
├── add.html                     # Form to submit a new complaint
│
├── css/
│   └── style.css                # Shared stylesheet for all pages
│
├── images/
│   └── sit.png                  # Silicon University logo
│
└── WEB-INF/
    ├── src/                     # Java source files
    │   ├── DBconnection.java    # Database credentials are configured here
    │   ├── AddComplaintServlet.java
    │   └── GetComplaintsServlet.java
    │
    ├── classes/                 # Compiled .class files
    │   ├── DBconnection.class
    │   ├── Complaint.class
    │   ├── AddComplaintServlet.class
    │   └── GetComplaintsServlet.class
    │
    └── lib/                     # Required JAR dependencies
        ├── gson-2.10.1.jar
        └── postgresql-42.7.10.jar
```

---

## Database Setup

### Step 1 — Start PostgreSQL

Ensure your PostgreSQL server is running before proceeding.

### Step 2 — Create the Database and Table

Run the provided `schema.sql` file:

```bash
psql -U postgres -f schema.sql
```

Or manually execute the following commands in `psql`:

```sql
CREATE DATABASE complaints;

\c complaints

CREATE TABLE complaints (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100)  NOT NULL,
    category    VARCHAR(50)   NOT NULL,
    title       VARCHAR(120)  NOT NULL,
    description VARCHAR(1000) NOT NULL,
    status      VARCHAR(20)   DEFAULT 'Pending',
    created_at  TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
);
```

### Step 3 — (Optional) Load Sample Data

```bash
psql -U postgres -d complaints -f sample_data.sql
```

---

## DB Credentials Configuration

Open the following file:

```
WEB-INF/src/DBconnection.java
```

Update the three lines below to match your PostgreSQL setup:

```java
private static String url      = "jdbc:postgresql://localhost:5432/complaints";
private static String username = "postgres";
private static String password = "your_password_here";
```

> **Security Warning:** Credentials are currently hardcoded directly in `DBconnection.java`. Ensure these values are updated before deployment, and avoid committing sensitive credentials to version control.

---

## Running the Project

### Step 1 — Clone the Repository

```bash
git clone https://github.com/your-username/Complaint_portal.git
cd Complaint_portal-main
```

### Step 2 — Compile the Java Source Files

From the project root, compile all `.java` files in `WEB-INF/src/` and output the compiled classes to `WEB-INF/classes/`:

```bash
javac -cp "WEB-INF/lib/*:/path/to/tomcat/lib/servlet-api.jar" \
      -d WEB-INF/classes \
      WEB-INF/src/*.java
```

> Replace `/path/to/tomcat/` with your actual Tomcat installation directory (e.g., `/opt/tomcat` or `C:\tomcat`).

### Step 3 — Deploy to Tomcat

Copy the entire project folder into Tomcat's `webapps/` directory and name it to match the context path referenced in the frontend JavaScript:

```bash
cp -r Complaint_portal-main /path/to/tomcat/webapps/complaintportalm1
```

The folder must be named **`complaintportalm1`** because the frontend makes requests to the following endpoints:

```
http://localhost:8080/complaintportalm1/complaints
http://localhost:8080/complaintportalm1/addComplaint
```

### Step 4 — Start Tomcat

```bash
# Linux / macOS
/path/to/tomcat/bin/startup.sh

# Windows
\path\to\tomcat\bin\startup.bat
```

### Step 5 — Open in Browser

```
http://localhost:8080/complaintportalm1/index.html
```

---

## API Endpoints

| Method | Endpoint        | Description                    |
|--------|-----------------|--------------------------------|
| GET    | `/complaints`   | Returns all complaints as JSON |
| POST   | `/addComplaint` | Submits a new complaint        |

**POST `/addComplaint` — Form Parameters:**

| Parameter     | Type   | Max Length | Required |
|---------------|--------|------------|----------|
| `name`        | String | 100        | Yes      |
| `category`    | String | 50         | Yes      |
| `title`       | String | 120        | Yes      |
| `description` | String | 1000       | Yes      |

---

## Dependencies (JARs)

Both JARs are included in `WEB-INF/lib/` — no manual download is required.

| JAR File                  | Purpose                       |
|---------------------------|-------------------------------|
| `gson-2.10.1.jar`         | Java object / JSON conversion |
| `postgresql-42.7.10.jar`  | PostgreSQL JDBC driver        |

Note: `servlet-api.jar` (or `jakarta.servlet-api`) is required at **compile time only**. It is provided by Tomcat and must **not** be placed in `WEB-INF/lib/`.

---

## Developers

- [sahusridhar23](https://github.com/sahusridhar23)
- [AmlanSP](https://github.com/AmlanSP)
- [Ayush-2604](https://github.com/Ayush-2604)
