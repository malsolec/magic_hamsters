CREATE TABLE manager_kid_activity (
	id				BIGSERIAL PRIMARY KEY,
    name			TEXT NOT NULL,
    img_url			TEXT NOT NULL DEFAULT 'picture',
    order_number	INTEGER NOT NULL,
    is_done			BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE manager_action (
	id				BIGSERIAL PRIMARY KEY,
	name			TEXT NOT NULL,
	img_url			TEXT NOT NULL DEFAULT 'picture',
	order_number	INTEGER NOT NULL,
	kid_activity_id BIGINT REFERENCES manager_kid_activity(id) ON DELETE CASCADE
);

CREATE TABLE manager_nfc_device (
	id				BIGSERIAL PRIMARY KEY,
	device_id		INTEGER NOT NULL DEFAULT 555,
    kid_activity_id BIGINT REFERENCES manager_kid_activity(id) ON DELETE CASCADE
);


