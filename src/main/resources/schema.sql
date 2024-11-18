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
                       roleId INT NOT NULL REFERENCES roles(id) ON DELETE RESTRICT

);

CREATE TABLE claimTypes(
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(50) UNIQUE NOT NULL,
                             active BOOLEAN DEFAULT TRUE,
                             dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             createdBy VARCHAR(50),
                             dateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             modifiedBy VARCHAR(50)
);
CREATE TABLE claimStatus (
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
                        policyNumber VARCHAR(50) NOT NULL,
                        claimTypeId INT NOT NULL REFERENCES claimTypes(id) ON DELETE RESTRICT,
                        incidentDate DATE NOT NULL,
                        amountClaimed NUMERIC(12, 2) NOT NULL,
                        claimStatusId INT NOT NULL REFERENCES claimStatus(id) ON DELETE RESTRICT,
                        active BOOLEAN DEFAULT TRUE,
                        dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        createdBy VARCHAR(50),
                        dateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        modifiedBy VARCHAR(50)
);



CREATE TABLE attachments (
                             id SERIAL PRIMARY KEY,
                             claimId INT NOT NULL REFERENCES claims(id) ON DELETE CASCADE,
                             type VARCHAR(50),
                             url VARCHAR(255) NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE paymentStatus (
                               id SERIAL PRIMARY KEY,
                               statusName VARCHAR(50) NOT NULL,
                               dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               createdBy VARCHAR(50),
                               dateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               modifiedBy VARCHAR(50)
);


CREATE TABLE payments (
                          id SERIAL PRIMARY KEY,
                          claimId INT NOT NULL REFERENCES claims(id) ON DELETE CASCADE,
                          amount NUMERIC(12, 2)  NOT NULL,
                          paymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          statusId INT NOT NULL REFERENCES paymentStatus(id) ON DELETE RESTRICT,
                          transactionReference VARCHAR(100) UNIQUE,
                          createdBy VARCHAR(50),
                          modifiedBy VARCHAR(50),
                          dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          dateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE workflowStage (
                               id SERIAL PRIMARY KEY,
                               stageName VARCHAR(50) UNIQUE NOT NULL,
                               dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               createdBy VARCHAR(50),
                               dateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               modifiedBy VARCHAR(50)
);

CREATE TABLE workflowStatus (
                                id SERIAL PRIMARY KEY,
                                statusName VARCHAR(50)  NOT NULL ,
                                dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                createdBy VARCHAR(50),
                                dateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                modifiedBy VARCHAR(50)
);

CREATE TABLE workflow (
                          id SERIAL PRIMARY KEY,
                          claimId INT REFERENCES claims(id) ON DELETE CASCADE,
                          assignedUserId INT REFERENCES users(id),
                          stageId INT REFERENCES workflowStage(id) ON DELETE RESTRICT,
                          statusId INT REFERENCES workflowStatus(id) ON DELETE RESTRICT,
                          dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          createdBy VARCHAR(50),
                          dateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          modifiedBy VARCHAR(50)
);
CREATE TABLE reports (
                         id SERIAL PRIMARY KEY,
                         reportName VARCHAR(100) NOT NULL,
                         reportData JSONB NOT NULL,
                         dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         dateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         createdBy VARCHAR(100),
                         modifiedBy VARCHAR(100)
);







