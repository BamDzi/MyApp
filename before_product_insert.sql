DELIMITER $$
CREATE TRIGGER before_product_insert 
    BEFORE INSERT ON product
    FOR EACH ROW
BEGIN
    INSERT INTO product_audit
    SET action = 'insert',
    product_name = NEW.name,
    user_id = NEW.vendor_user_id,
    active = NEW.active,
    purchase_price = NEW.purchase_price,
    description = NEW.description,
    components = NEW.components,
    tips = NEW.tips,
	change_date = NOW(); 
END$$
DELIMITER ;