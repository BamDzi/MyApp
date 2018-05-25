DELIMITER $$
CREATE TRIGGER before_product_update 
    BEFORE UPDATE ON product
    FOR EACH ROW
BEGIN
    INSERT INTO product_audit
    SET action = 'update',
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