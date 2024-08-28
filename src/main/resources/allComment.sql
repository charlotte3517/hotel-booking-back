INSERT INTO Abbreviation_Table(
       abbreviation_id, mother_code, child_code,
       description, language)
VALUES(
       abbreviation_table_seq.nextval, 'HTS', 'R',
       'Reservation', 'EN');


INSERT INTO Abbreviation_Table(
       abbreviation_id, mother_code, child_code,
       description, language)
VALUES(
       abbreviation_table_seq.nextval, 'HTS', 'C',
       'Confirmation', 'EN');


INSERT INTO Abbreviation_Table(
       abbreviation_id, mother_code, child_code,
       description, language)
VALUES(
       abbreviation_table_seq.nextval, 'HTS', 'N',
       'Canceled', 'EN');


INSERT INTO Abbreviation_Table(
       abbreviation_id, mother_code, child_code,
       description, language)
VALUES(
        abbreviation_table_seq.nextval, 'PMM', 'CD',
        'Credit Card', 'EN');


INSERT INTO Web_User(
       user_id, user_name, password_hash,
       email, created_at, created_user_id,
       updated_at, updated_user_id)
VALUES(
       0, 'user', '1234',
       'user@gmail.com', current_timestamp, 0,
       current_timestamp, 0);


INSERT INTO Bed_Type(
       bed_type_id, bed_type_name, bed_count)
VALUES(
       0, 'Single Bed', 1);


INSERT INTO Hotel(
         hotel_id, hotel_name, address,
         google_map_url, google_map_place_id, rating,
         created_at, created_user_id, updated_at,
         updated_user_id)
VALUES(
         0, 'Hotel A', 'Address A',
         'https://www.google.com/maps', 'ChIJ2VD9gox2bjQRJ159S3A7frk', 4.5,
         current_timestamp, 0, current_timestamp,
         0);


INSERT INTO Room_Type(
       room_type_id, hotel_id, room_name,
       min_people, max_people, bed_type_id,
       normal_day_price, holiday_price, room_capacity,
       private_bath, wifi, breakfast,
       mini_bar, room_service, television,
       air_conditioner, refrigerator, smoke_free,
       child_friendly, pet_friendly, created_at,
       created_user_id, updated_at, updated_user_id)
VALUES(
       1, 0, 'Single Room',
       1, 1, 0,
       100.00, 110.00, 1,
       1, 1, 1,
       1, 1, 1,
       1, 1, 1,
       1, 1, current_timestamp,
       0, current_timestamp, 0);


INSERT INTO Room_Type(
    room_type_id, hotel_id, room_name,
    min_people, max_people, bed_type_id,
    normal_day_price, holiday_price, room_capacity,
    private_bath, wifi, breakfast,
    mini_bar, room_service, television,
    air_conditioner, refrigerator, smoke_free,
    child_friendly, pet_friendly, created_at,
    created_user_id, updated_at, updated_user_id)
VALUES(
          2, 0, 'Double Room',
          1, 2, 0,
          200.00, 220.00, 1,
          1, 1, 1,
          1, 1, 1,
          1, 1, 1,
          1, 1, current_timestamp,
          0, current_timestamp, 0);


INSERT INTO Room_Type(
    room_type_id, hotel_id, room_name,
    min_people, max_people, bed_type_id,
    normal_day_price, holiday_price, room_capacity,
    private_bath, wifi, breakfast,
    mini_bar, room_service, television,
    air_conditioner, refrigerator, smoke_free,
    child_friendly, pet_friendly, created_at,
    created_user_id, updated_at, updated_user_id)
VALUES(
          3, 0, 'Triple Room',
          1, 3, 0,
          300.00, 330.00, 1,
          1, 1, 1,
          1, 1, 1,
          1, 1, 1,
          1, 1, current_timestamp,
          0, current_timestamp, 0);


INSERT INTO Room_Type(
    room_type_id, hotel_id, room_name,
    min_people, max_people, bed_type_id,
    normal_day_price, holiday_price, room_capacity,
    private_bath, wifi, breakfast,
    mini_bar, room_service, television,
    air_conditioner, refrigerator, smoke_free,
    child_friendly, pet_friendly, created_at,
    created_user_id, updated_at, updated_user_id)
VALUES(
          4, 0, 'Quadruple Room',
          1, 4, 0,
          400.00, 440.00, 1,
          1, 1, 1,
          1, 1, 1,
          1, 1, 1,
          1, 1, current_timestamp,
          0, current_timestamp, 0);