CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL,
                       active BOOLEAN DEFAULT TRUE,
                       dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       createdBy VARCHAR(50),
                       dateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       modifiedBy VARCHAR(50)
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       fullName VARCHAR(100) NOT NULL,
                       phoneNumber VARCHAR(20),
                       email VARCHAR(100) UNIQUE NOT NULL,
                       dateOfBirth DATE,
                       gender VARCHAR(10),
                       active BOOLEAN DEFAULT TRUE,
                       dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       createdBy VARCHAR(50),
                       dateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       modifiedBy VARCHAR(50),
                       role_id INT NOT NULL REFERENCES roles(id) ON DELETE RESTRICT

);

CREATE TABLE claim_types(
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(50) UNIQUE NOT NULL,
                             active BOOLEAN DEFAULT TRUE,
                             dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             createdBy VARCHAR(50),
                             dateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             modifiedBy VARCHAR(50)
);
CREATE TABLE claim_status (
                              id SERIAL PRIMARY KEY,
                              name VARCHAR(50) UNIQUE NOT NULL,
                              active BOOLEAN DEFAULT TRUE,
                              dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              createdBy VARCHAR(50),
                              dateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              modifiedBy VARCHAR(50)
);


CREATE TABLE claims (
                        id SERIAL PRIMARY KEY,
                        policy_number VARCHAR(50) NOT NULL,
                        claim_type_id INT NOT NULL REFERENCES claim_types(id) ON DELETE RESTRICT,
                        incident_date DATE NOT NULL,
                        amount_claimed NUMERIC(12, 2) NOT NULL,
                        claim_status_id INT NOT NULL REFERENCES claim_status(id) ON DELETE RESTRICT,
                        active BOOLEAN DEFAULT TRUE,
                        dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        createdBy VARCHAR(50),
                        dateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        modifiedBy VARCHAR(50)
);


CREATE TABLE attachments (
                             id SERIAL PRIMARY KEY,
                             claim_id INT NOT NULL REFERENCES claims(id) ON DELETE CASCADE,
                             type VARCHAR(50),
                             url VARCHAR(255) NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


