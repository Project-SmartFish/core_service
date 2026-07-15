-- Habilita a extensão PostGIS
CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE user_account (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    access_level VARCHAR(30) NOT NULL
        CHECK (access_level IN ('ADMIN', 'USER'))
);

CREATE TABLE fish_spot (
    id UUID PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    location GEOGRAPHY(Point, 4326) NOT NULL,
    water_type VARCHAR(30) NOT NULL
        CHECK (water_type IN ('RIVER', 'LAKE', 'FISHING_POND', 'SEA', 'DAM'))
);


CREATE TABLE fishing_event (
    id UUID PRIMARY KEY,
    fish_spot_id UUID NOT NULL,

    fishing_status VARCHAR(30) NOT NULL
        CHECK (fishing_status IN ('OPEN', 'CLOSED', 'CANCELLED')),

    event_date DATE NOT NULL,
    event_time TIME NOT NULL,

    CONSTRAINT fk_fishing_event_fish_spot
        FOREIGN KEY (fish_spot_id)
        REFERENCES fish_spot(id)
);

CREATE TABLE user_fishing_event (
    id UUID PRIMARY KEY,

    user_account_id UUID NOT NULL,
    fishing_event_id UUID NOT NULL,

    CONSTRAINT fk_user_fishing_event_user
        FOREIGN KEY (user_account_id)
        REFERENCES user_account(id),

    CONSTRAINT fk_user_fishing_event_event
        FOREIGN KEY (fishing_event_id)
        REFERENCES fishing_event(id),

    CONSTRAINT uk_user_fishing_event
        UNIQUE (user_account_id, fishing_event_id)
);

CREATE TABLE personal_fishing (
    id UUID PRIMARY KEY,

    user_fishing_event_id UUID NOT NULL,

    species VARCHAR(100) NOT NULL,

    amount INT NOT NULL
        CHECK (amount > 0),

    estimated_weight_kg DECIMAL(5,2) NOT NULL
        CHECK (estimated_weight_kg >= 0),

    CONSTRAINT fk_personal_fishing_user_event
        FOREIGN KEY (user_fishing_event_id)
        REFERENCES user_fishing_event(id)
);