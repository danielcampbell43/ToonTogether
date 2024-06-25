-- Remove the existing track_data column
ALTER TABLE songs DROP COLUMN IF EXISTS track_data;

-- Add new columns for individual track information
ALTER TABLE songs
    ADD COLUMN track_name VARCHAR(255),
    ADD COLUMN track_artist VARCHAR(255),
    ADD COLUMN album VARCHAR(255),
    ADD COLUMN release_year VARCHAR(4),  -- Assuming release year is stored as a string
    ADD COLUMN image VARCHAR(255);

