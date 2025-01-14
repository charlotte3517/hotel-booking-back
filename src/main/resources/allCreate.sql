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