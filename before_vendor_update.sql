DELIMITER $$
CREATE TRIGGER before_vendor_update 
    BEFORE UPDATE ON vendor
    FOR EACH ROW
BEGIN
    INSERT INTO vendor_audit
    SET action = 'update',
    vendor_id = NEW.user_user_id,
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