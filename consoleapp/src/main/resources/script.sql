CREATE TABLE IF NOT EXISTS countries (
                                         country_id UUID PRIMARY KEY,
                                         country_name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS counties (
                                        county_id UUID PRIMARY KEY,
                                        county_name VARCHAR(255) NOT NULL,
    country_id UUID REFERENCES countries(country_id)
    );

CREATE TABLE IF NOT EXISTS towns (
                                     town_id UUID PRIMARY KEY,
                                     town_name VARCHAR(255) NOT NULL,
    county_id UUID REFERENCES counties(county_id),
    country_id UUID REFERENCES countries(country_id)
    );

CREATE TABLE IF NOT EXISTS address_lines (
                                             address_line_id UUID PRIMARY KEY,
                                             address_line VARCHAR(255),
    town_id UUID REFERENCES towns(town_id)
    );

CREATE TABLE IF NOT EXISTS postcodes (
                                         postcode_id UUID PRIMARY KEY,
                                         postcode VARCHAR(20) NOT NULL,
    town_id UUID REFERENCES towns(town_id)
    );

CREATE TABLE IF NOT EXISTS customers (
                                         customer_ref UUID PRIMARY KEY,
                                         customer_name VARCHAR(255) NOT NULL,
    address_line_1_id UUID REFERENCES address_lines(address_line_id),
    address_line_2_id UUID REFERENCES address_lines(address_line_id),
    town_id UUID REFERENCES towns(town_id),
    postcode_id UUID REFERENCES postcodes(postcode_id)
    );

CREATE INDEX IF NOT EXISTS idx_town_id ON customers(town_id);
CREATE INDEX IF NOT EXISTS idx_postcode_id ON customers(postcode_id);
CREATE INDEX IF NOT EXISTS idx_address_line_1_id ON customers(address_line_1_id);
