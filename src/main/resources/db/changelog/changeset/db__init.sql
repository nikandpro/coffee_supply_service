CREATE TABLE IF NOT EXISTS brigade (
    id              UUID PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS country_stats (
    id              bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    country         VARCHAR(100)  NOT NULL
);

CREATE TABLE IF NOT EXISTS grain (
    id                 bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    weight             INT           NOT NULL,
    origin_country     BIGINT REFERENCES country_stats (id) ON DELETE CASCADE,
    robusta_percentage DECIMAL(5, 2) NOT NULL,
    arabica_percentage DECIMAL(5, 2) NOT NULL,
    grain_type         VARCHAR(100)  NOT NULL
);

CREATE TABLE IF NOT EXISTS roasting (
    id             bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    grain_id       BIGINT REFERENCES grain (id) ON DELETE CASCADE,
    brigade_id     UUID REFERENCES brigade (id) ON DELETE CASCADE,
    country_id     BIGINT REFERENCES country_stats (id) ON DELETE CASCADE,
    quantity_taken INT NOT NULL,
    weight_out     INT NOT NULL
);

