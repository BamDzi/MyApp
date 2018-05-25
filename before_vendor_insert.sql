DELIMITER $$
CREATE TRIGGER before_vendor_insert 
    BEFORE INSERT ON vendor
    FOR EACH ROW
BEGIN
    INSERT INTO vendor_audit
    SET action = 'insert',
    vendor_id = NEW.id,
    email = NEW.email,
    introduction = NEW.introduction,
    locality = NEW.locality,
    name = NEW.name,
    nip = NEW.nip,
    phone_number = NEW.phone_number,
    street = NEW.street,
    street_number = NEW.street_number,
    zip_code = NEW.zip_code,
	change_date = NOW(); 
END$$
DELIMITER ;