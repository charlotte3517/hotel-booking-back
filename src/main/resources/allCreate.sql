CREATE TABLE Web_User (
                          user_id         NUMBER(10, 0) PRIMARY KEY,
                          user_name       VARCHAR2(50) NOT NULL,
                          password_hash   VARCHAR2(100) NOT NULL,
                          email           VARCHAR2(100) NOT NULL,
                          created_at      DATE NOT NULL,
                          created_user_id NUMBER(10, 0) NOT NULL,
                          updated_at      DATE,
                          updated_user_id NUMBER(10, 0)
);

CREATE SEQUENCE web_user_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE Hotel (
                       hotel_id            NUMBER(10, 0) PRIMARY KEY,
                       hotel_name          VARCHAR2(100) NOT NULL,
                       address             VARCHAR2(200),
                       google_map_url      VARCHAR2(200),
                       google_map_place_id VARCHAR2(200) NOT NULL,
                       rating              NUMBER(2,1),
                       created_at          DATE NOT NULL,
                       created_user_id     NUMBER(10, 0) NOT NULL,
                       updated_at          DATE,
                       updated_user_id     NUMBER(10, 0)
);

CREATE INDEX idx_hotel_google_map_place_id ON Hotel(google_map_place_id);

CREATE SEQUENCE hotel_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE Hotel_Order (
                             order_id        NUMBER(10, 0) PRIMARY KEY,
                             hotel_id        NUMBER(10, 0) NOT NULL,
                             check_in_date   DATE NOT NULL,
                             check_out_date  DATE NOT NULL,
                             user_id         NUMBER(10, 0) NOT NULL,
                             total_price     NUMBER(10, 2) NOT NULL,
                             status          VARCHAR2(1) NOT NULL,
                             is_paid         NUMBER(1, 0) NOT NULL,
                             created_at      DATE NOT NULL,
                             created_user_id NUMBER(10, 0) NOT NULL,
                             updated_at      DATE,
                             updated_user_id NUMBER(10, 0),
                             CONSTRAINT fk_hotel_order_hotel_id FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id)
);

COMMENT ON COLUMN Hotel_Order.status IS 'Hotel Status<HTS>';

CREATE INDEX idx_hotel_order_hotel_id ON Hotel_Order(hotel_id);

CREATE SEQUENCE hotel_order_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE Google_Review (
                               google_review_id          NUMBER(10, 0) PRIMARY KEY,
                               google_map_place_id       VARCHAR2(200) NOT NULL,
                               author_name               VARCHAR2(50),
                               rating                    NUMBER(2,1),
                               content                   CLOB,
                               review_time               VARCHAR2(20),
                               created_at                DATE NOT NULL
);

CREATE SEQUENCE google_review_seq
    START WITH 1
    INCREMENT BY 1;

CREATE INDEX idx_google_review_google_map_place_id ON Google_Review(google_map_place_id);

CREATE TABLE Bed_Type (
                          bed_type_id   NUMBER(3, 0) PRIMARY KEY,
                          bed_type_name VARCHAR2(50) NOT NULL,
                          bed_count     NUMBER(1, 0) NOT NULL
);

CREATE SEQUENCE bed_type_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE Room_Type (
                           room_type_id     NUMBER(10, 0) PRIMARY KEY,
                           hotel_id         NUMBER(10, 0) NOT NULL,
                           room_name        VARCHAR2(100) NOT NULL,
                           min_people       NUMBER(1, 0) NOT NULL,
                           max_people       NUMBER(1, 0) NOT NULL,
                           bed_type_id      NUMBER(3, 0) NOT NULL,
                           normal_day_price NUMBER(10, 2) NOT NULL,
                           holiday_price    NUMBER(10, 2) NOT NULL,
                           room_capacity    NUMBER(3, 0) NOT NULL,
                           private_bath     NUMBER(1, 0) NOT NULL,
                           wifi             NUMBER(1, 0) NOT NULL,
                           breakfast        NUMBER(1, 0) NOT NULL,
                           mini_bar         NUMBER(1, 0) NOT NULL,
                           room_service     NUMBER(1, 0) NOT NULL,
                           television       NUMBER(1, 0) NOT NULL,
                           air_conditioner  NUMBER(1, 0) NOT NULL,
                           refrigerator     NUMBER(1, 0) NOT NULL,
                           smoke_free       NUMBER(1, 0) NOT NULL,
                           child_friendly   NUMBER(1, 0) NOT NULL,
                           pet_friendly     NUMBER(1, 0) NOT NULL,
                           created_at       DATE NOT NULL,
                           created_user_id  NUMBER(10, 0) NOT NULL,
                           updated_at       DATE,
                           updated_user_id  NUMBER(10, 0),
                           CONSTRAINT fk_room_type_hotel_id FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id),
                           CONSTRAINT fk_room_type_bed_type_id FOREIGN KEY (bed_type_id) REFERENCES Bed_Type(bed_type_id)
);

CREATE SEQUENCE room_type_seq
    START WITH 5
    INCREMENT BY 1;

CREATE INDEX idx_room_type_hotel_id ON Room_Type(hotel_id);

CREATE TABLE Daily_Room_Availability (
                                         room_availability_id NUMBER(10, 0) PRIMARY KEY,
                                         hotel_id             NUMBER(10, 0) NOT NULL,
                                         room_type_id         NUMBER(10, 0) NOT NULL,
                                         stay_date            DATE NOT NULL,
                                         available_rooms      NUMBER(4, 0) NOT NULL,
                                         sold_rooms           NUMBER(4, 0) NOT NULL,
                                         bookable_rooms       NUMBER(4, 0) NOT NULL,
                                         CONSTRAINT fk_room_avail_hotel FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id),
                                         CONSTRAINT fk_room_avail_room_type FOREIGN KEY (room_type_id) REFERENCES Room_Type(room_type_id)
);

CREATE SEQUENCE room_avail_seq
    START WITH 1
    INCREMENT BY 1;

CREATE INDEX idx_room_avail_hotel_id ON Daily_Room_Availability(hotel_id);
CREATE INDEX idx_room_avail_room_type_id ON Daily_Room_Availability(room_type_id);
CREATE INDEX idx_room_avail_stay_date ON Daily_Room_Availability(stay_date);

CREATE TABLE Order_Detail (
                              order_detail_id NUMBER(10, 0) PRIMARY KEY,
                              order_id        NUMBER(10, 0) NOT NULL,
                              room_type_id    NUMBER(10, 0) NOT NULL,
                              room_quantity   NUMBER(4, 0) NOT NULL,
                              price           NUMBER(10, 2) NOT NULL,
                              created_at      DATE NOT NULL,
                              created_user_id NUMBER(10, 0) NOT NULL,
                              updated_at      DATE,
                              updated_user_id NUMBER(10, 0),
                              CONSTRAINT fk_order_detail_order_id FOREIGN KEY (order_id) REFERENCES Hotel_Order(order_id),
                              CONSTRAINT fk_order_detail_room_type_id FOREIGN KEY (room_type_id) REFERENCES Room_Type(room_type_id)
);

CREATE SEQUENCE order_detail_seq
    START WITH 1
    INCREMENT BY 1;

CREATE INDEX idx_order_detail_order_id ON Order_Detail(order_id);

CREATE TABLE Payment (
                         payment_id       NUMBER(10, 0) PRIMARY KEY,
                         order_id         NUMBER(10, 0) NOT NULL,
                         payment_date     DATE NOT NULL,
                         payment_amount   NUMBER(10, 2) NOT NULL,
                         payment_method   VARCHAR2(50) NOT NULL,
                         created_at       DATE NOT NULL,
                         created_user_id  NUMBER(10, 0) NOT NULL,
                         updated_at       DATE,
                         updated_user_id  NUMBER(10, 0),
                         CONSTRAINT fk_payment_order_id FOREIGN KEY (order_id) REFERENCES Hotel_Order(order_id)
);

COMMENT ON COLUMN Payment.payment_method IS 'Payment Method<PMM>';

CREATE SEQUENCE payment_seq
    START WITH 1
    INCREMENT BY 1;

CREATE INDEX idx_payment_order_id ON Payment(order_id);

CREATE TABLE Abbreviation_Table (
                                    abbreviation_id   NUMBER(10, 0) PRIMARY KEY,
                                    mother_code       VARCHAR2(3) NOT NULL,
                                    child_code        VARCHAR2(3) NOT NULL,
                                    description       VARCHAR2(50) NOT NULL,
                                    language          VARCHAR2(2) NOT NULL
);

CREATE SEQUENCE abbreviation_table_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE External_Request_Log (
                                      request_id NUMBER(10, 0) PRIMARY KEY,
                                      request_method VARCHAR2(10) NOT NULL,
                                      request_url VARCHAR2(500) NOT NULL,
                                      request_headers CLOB,
                                      request_body CLOB,
                                      response_status NUMBER(3, 0),
                                      response_headers CLOB,
                                      response_body CLOB,
                                      client_ip VARCHAR2(45),
                                      request_time DATE,
                                      response_time DATE,
                                      created_at DATE NOT NULL
);

CREATE SEQUENCE external_request_log_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE Google_API_Request_Log (
                                        request_id NUMBER(10, 0) PRIMARY KEY,
                                        api_url VARCHAR2(500) NOT NULL,
                                        request_headers CLOB,
                                        request_body CLOB,
                                        response_status NUMBER(3, 0),
                                        response_headers CLOB,
                                        response_body CLOB,
                                        request_time DATE,
                                        response_time DATE,
                                        created_at DATE NOT NULL
);

CREATE SEQUENCE google_api_request_log_seq
    START WITH 1
    INCREMENT BY 1;