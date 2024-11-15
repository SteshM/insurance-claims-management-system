INSERT INTO roles (name) VALUES ('insurer'), ('customer');
INSERT INTO claim_types (name) VALUES ('medical'), ('vehicle'), ('property');
INSERT INTO claim_status (name) VALUES ('pending'), ('approved'), ('rejected');
INSERT INTO users (
    username,
    password,
    fullName,
    phoneNumber,
    email,
    dateOfBirth,
    gender,
    active,
    dateCreated,
    createdBy,
    dateModified,
    modifiedBy,
    role_id
) VALUES (
             'janedoe',
             'hashedpassword123',
             'Jane Doe',
             '555-1234',
             'janedoe@example.com',
             '1995-07-15',
             'Female',
             TRUE,
             CURRENT_TIMESTAMP,
             'insurer',
             CURRENT_TIMESTAMP,
             'insurer',
             1

         );INSERT INTO users (
    username,
    password,
    fullName,
    phoneNumber,
    email,
    dateOfBirth,
    gender,
    active,
    dateCreated,
    createdBy,
    dateModified,
    modifiedBy,
    role_id
) VALUES (
             'john',
             'password123',
             'John Doe',
             '555-1236',
             'johndoe@example.com',
             '1995-05-15',
             'Male',
             TRUE,
             CURRENT_TIMESTAMP,
             'customer',
             CURRENT_TIMESTAMP,
             'customer',
             2
         );
