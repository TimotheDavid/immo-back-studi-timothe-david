INSERT INTO immo.civility(uuid, civility) VALUES (gen_random_uuid(), 'monsieur');
INSERT INTO immo.civility(uuid, civility) VALUES (gen_random_uuid(), 'madame');
INSERT INTO immo.civility(uuid, civility) VALUES (gen_random_uuid(), 'autre');

INSERT INTO immo.origin(uuid, origin) VALUES (gen_random_uuid(), 'proprietaire');
INSERT INTO immo.origin(uuid, origin) VALUES (gen_random_uuid(), 'CAF');


INSERT INTO immo.sens_payment(uuid, sense, action) VALUES (gen_random_uuid(), 'ajouter', true);
INSERT INTO immo.sens_payment(uuid, sense, action) VALUES (gen_random_uuid(), 'enlever', false);

INSERT INTO immo.type_payment(uuid, type,senspaymentid) VALUES (gen_random_uuid(),'Carte',(SELECT uuid FROM immo.sens_payment  WHERE id = 1));
INSERT INTO immo.type_payment(uuid, type,senspaymentid) VALUES (gen_random_uuid(),  'Espece',(SELECT uuid FROM immo.sens_payment WHERE id = 2));
INSERT INTO immo.type_payment(uuid, type,senspaymentid) VALUES (gen_random_uuid(),  'Cheque',(SELECT uuid FROM immo.sens_payment WHERE id = 1));
INSERT INTO immo.type_payment(uuid, type,senspaymentid) VALUES (gen_random_uuid(),  'Cheque',(SELECT uuid FROM immo.sens_payment  WHERE id = 2));