
ALTER TABLE immo.rent
ADD COLUMN IF NOT EXISTS  in_description_tenant varchar;

ALTER TABLE immo.rent
ADD COLUMN IF NOT EXISTS out_description_tenant varchar;