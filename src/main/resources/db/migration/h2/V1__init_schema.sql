CREATE TABLE user_account (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    access_level VARCHAR(10) NOT NULL
        CHECK (access_level IN ('ADMIN', 'USER'))
);

CREATE TABLE fish_spot (
    id UUID PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    location GEOMETRY,
    water_type VARCHAR(20) NOT NULL
        CHECK (water_type IN ('RIVER', 'LAKE', 'FISHING_POND', 'SEA', 'DAM'))
);

CREATE TABLE fishing_event (
    id UUID PRIMARY KEY,
    id_fish_spot UUID NOT NULL,
    fishing_status VARCHAR(20) NOT NULL
        CHECK (fishing_status IN ('OPEN', 'CLOSED', 'CANCELLED')),
    event_date DATE NOT NULL,
    event_time TIME NOT NULL,

    CONSTRAINT fk_fishing_event_fish_spot
        FOREIGN KEY (id_fish_spot)
        REFERENCES fish_spot(id)
);

CREATE TABLE user_fishing_event (
    id UUID PRIMARY KEY,
    id_user UUID NOT NULL,
    id_fishing_event UUID NOT NULL,

    CONSTRAINT fk_user_fishing_event_user
        FOREIGN KEY (id_user)
        REFERENCES user_account(id),

    CONSTRAINT fk_user_fishing_event_event
        FOREIGN KEY (id_fishing_event)
        REFERENCES fishing_event(id),

    CONSTRAINT uk_user_event
        UNIQUE (id_user, id_fishing_event)
);

CREATE TABLE personal_fishing (
    id UUID PRIMARY KEY,
    id_user_fishing_event UUID NOT NULL,
    species VARCHAR(100) NOT NULL,
    amount INT NOT NULL,
    estimated_weight_kg DECIMAL(5,2) NOT NULL,

    CONSTRAINT fk_personal_fishing_user_event
        FOREIGN KEY (id_user_fishing_event)
        REFERENCES user_fishing_event(id)
);