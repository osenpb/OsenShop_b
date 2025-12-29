INSERT INTO category (name)
VALUES
  ('Audífonos'),
  ('Celulares'),
  ('Teclados');


INSERT INTO products (
  name,
  description,
  price,
  stock,
  image_url,
  created_at,
  is_active,
  category_id
)
VALUES
  ('Audífonos Sony WH-1000XM5', 'Audífonos inalámbricos con cancelación de ruido de Sony', 1499.00, 12,
   'https://example.com/images/sony-wh1000xm5.jpg',
   NOW(), TRUE, 1),

  ('Audífonos Bose QuietComfort 45', 'Audífonos Bose con excelente cancelación de ruido y comodidad', 1399.00, 8,
   'https://example.com/images/bose-qc45.jpg',
   NOW(), TRUE, 1),

  ('Audífonos Inalámbricos JBL Tune 230NC', 'Audífonos JBL inalámbricos con sonido potente y ligero', 399.00, 20,
   'https://example.com/images/jbl-230nc.jpg',
   NOW(), TRUE, 1);


INSERT INTO products (
  name,
  description,
  price,
  stock,
  image_url,
  created_at,
  is_active,
  category_id
)
VALUES
  ('Samsung Galaxy S23', 'Smartphone Samsung con cámara avanzada y pantalla AMOLED', 3299.00, 10,
   'https://example.com/images/galaxy-s23.jpg',
   NOW(), TRUE, 2),

  ('iPhone 14', 'iPhone 14 con chip A15 y cámara dual avanzada', 3899.00, 7,
   'https://example.com/images/iphone-14.jpg',
   NOW(), TRUE, 2),

  ('Xiaomi Redmi Note 12', 'Smartphone Xiaomi económico con buena batería y pantalla AMOLED', 1099.00, 18,
   'https://example.com/images/redmi-note-12.jpg',
   NOW(), TRUE, 2);


INSERT INTO products (
  name,
  description,
  price,
  stock,
  image_url,
  created_at,
  is_active,
  category_id
)
VALUES
  ('Teclado Mecánico Logitech G Pro X', 'Teclado mecánico para gamers con switches personalizables', 699.00, 15,
   'https://example.com/images/logitech-gpro-x.jpg',
   NOW(), TRUE, 3),

  ('Teclado Mecánico Redragon Kumara K552', 'Teclado mecánico económico con retroiluminación LED', 249.00, 30,
   'https://example.com/images/redragon-k552.jpg',
   NOW(), TRUE, 3),

  ('Teclado Inalámbrico Keychron K6', 'Teclado compacto e inalámbrico con compatibilidad Mac/Windows', 459.00, 12,
   'https://example.com/images/keychron-k6.jpg',
   NOW(), TRUE, 3);
