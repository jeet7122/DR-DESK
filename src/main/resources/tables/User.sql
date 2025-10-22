CREATE TABLE users(
    user_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(255) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

CREATE OR REPLACE FUNCTION update_last_modified_date()
RETURNS TRIGGER AS $$
    BEGIN
        IF new is distinct from old THEN
            new.updated_at = now();
        end if;
        return new;
    end;
    $$ LANGUAGE plpgsql;


CREATE TRIGGER tr_users_update_date
    BEFORE UPDATE on users
    FOR EACH row
    EXECUTE FUNCTION update_last_modified_date();