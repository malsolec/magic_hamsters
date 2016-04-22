CREATE TABLE manager_action (
	id				BIGSERIAL PRIMARY KEY,
	name			TEXT NOT NULL,
	img_url			TEXT NOT NULL DEFAULT 'picture',
	order_number	INTEGER NOT NULL,
	kid_activity_id BIGINT REFERENCES manager_kid_activity(id) ON DELETE CASCADE
);

CREATE TABLE manager_nfc_device (
	id				BIGSERIAL PRIMARY KEY,
	device_id		INTEGER NOT NULL, DEFAULT 555,
    kid_activity_id BIGINT REFERENCES manager_kid_activity(id) ON DELETE CASCADE
);

CREATE TABLE manager_kid_activity (
	id				BIGSERIAL PRIMARY KEY,
    name			TEXT NOT NULL,
    img_url			TEXT NOT NULL DEFAULT 'picture',
    order_number	INTEGER NOT NULL,
    is_done			BOOLEAN NOT NULL DEFAULT FALSE
);


INSERT INTO manager_kid_activity (name, img_url, order_number) VALUES
	('Mycie zębów', 'szczoteczka', 1),
	('Zabawa z misiem', 'mis', 2),
	('Kodowanie', 'koduj', 3);

INSERT INTO manager_nfc_device (device_id, kid_activity_id) VALUES
	(28, (SELECT id FROM manager_kid_activity WHERE order_number = 1)),
	(4, (SELECT id FROM manager_kid_activity WHERE order_number = 2)),
	(-193, (SELECT id FROM manager_kid_activity WHERE order_number = 3));

INSERT INTO manager_action (name, img_url, order_number, kid_activity_id) VALUES
	('Weź szczoteczkę', 'szczoteczka_duza', 1, (SELECT id FROM manager_kid_activity WHERE order_number = 1)),
	('Nałóż pastę', 'szczoteczka_duza', 2, (SELECT id FROM manager_kid_activity WHERE order_number = 1)),
	('Szczotkuj zęby', 'szczoteczka_duza', 3, (SELECT id FROM manager_kid_activity WHERE order_number = 1)),
	('Wypluj pastę', 'szczoteczka_duza', 4, (SELECT id FROM manager_kid_activity WHERE order_number = 1)),
	('Wypłucz usta', 'szczoteczka_duza', 5, (SELECT id FROM manager_kid_activity WHERE order_number = 1)),
	('Umyj i odłóż szczoteczkę', 'szczoteczka_duza', 6, (SELECT id FROM manager_kid_activity WHERE order_number = 1)),
	('Zapytaj misia: czy dobrze się czujesz?', 'mis_duzy', 1, (SELECT id FROM manager_kid_activity WHERE order_number = 2)),
	('Miś odpowiada: boli mnie łapka', 'mis_duzy', 2, (SELECT id FROM manager_kid_activity WHERE order_number = 2)),
	('Przyklej misiowi plaster', 'mis_duzy', 3, (SELECT id FROM manager_kid_activity WHERE order_number = 2)),
	('Zapytaj misia: czy teraz jest lepiej?', 'mis_duzy', 4, (SELECT id FROM manager_kid_activity WHERE order_number = 2)),
	('Miś odpowiada: Tak. Dziękuję.', 'mis_duzy', 5, (SELECT id FROM manager_kid_activity WHERE order_number = 2)),
	('Przytul misia', 'mis_duzy', 6, (SELECT id FROM manager_kid_activity WHERE order_number = 2)),
	('Włącz komputer', 'koduj_duzy', 1, (SELECT id FROM manager_kid_activity WHERE order_number = 3)),
	('Odpal IDE', 'koduj_duzy', 2, (SELECT id FROM manager_kid_activity WHERE order_number = 3)),
	('Uratuj świat', 'koduj_duzy', 3, (SELECT id FROM manager_kid_activity WHERE order_number = 3)),
	('Możesz włączyć koty na youtube', 'koduj_duzy', 4, (SELECT id FROM manager_kid_activity WHERE order_number = 2));


