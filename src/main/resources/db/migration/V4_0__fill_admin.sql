INSERT INTO users(user_id, email, full_name, embedding, password, enabled)
SELECT * FROM (SELECT '0884831b-42f9-4f81-af88-3e777be69c8f', 'rasknastya@gmail.com', 'Анастасия Сергеевна Крупина',
                      ARRAY [-0.0750,  0.0492, -0.0326, -0.0822,  0.0210, -0.0253,  0.0155, -0.0203,
    0.0477,  0.0167,  0.0073, -0.0207, -0.0171, -0.0971, -0.0354, -0.0020,
    0.0214,  0.0448,  0.0007, -0.0121, -0.0546, -0.0060,  0.0388,  0.0084,
    0.0150, -0.0048, -0.0117, -0.0468, -0.0121,  0.0651, -0.0264,  0.0347,
    0.0006,  0.0129,  0.0538,  0.0523,  0.0579, -0.0142,  0.0042,  0.0665,
    0.0161, -0.0977, -0.0487,  0.0441, -0.0219, -0.0288, -0.0290,  0.0163,
    0.0218,  0.0101, -0.0219, -0.0618, -0.0618,  0.0682,  0.0463,  0.0628,
    -0.0491,  0.0358,  0.0143, -0.0539,  0.0213,  0.0143, -0.0431,  0.0107,
    0.0042, -0.0476, -0.0351,  0.0427, -0.0416,  0.0223,  0.0145, -0.0217,
    -0.0319, -0.0392, -0.0175, -0.0460, -0.0694,  0.0313,  0.0385, -0.0515,
    -0.0117,  0.0048,  0.0203, -0.0381, -0.0223,  0.0279, -0.0695,  0.0220,
    0.0810,  0.0018,  0.0155, -0.0034,  0.0024,  0.0009,  0.0066, -0.0423,
    -0.0186,  0.0534, -0.0347,  0.0109, -0.0177, -0.0032, -0.0272, -0.0063,
    -0.0142,  0.0042,  0.0218, -0.0249, -0.0042,  0.0051, -0.0474,  0.0075,
    0.0175,  0.0110,  0.0010,  0.0509,  0.0342,  0.0523, -0.0297, -0.0207,
    0.0125, -0.0680,  0.0226,  0.0707,  0.0113,  0.0282, -0.0081,  0.0050,
    -0.0676, -0.0048, -0.0223, -0.0281, -0.0208, -0.0609,  0.0540, -0.0431,
    -0.0284, -0.0325, -0.0329,  0.0053, -0.0513, -0.0440, -0.0627,  0.0445,
    -0.0497, -0.0053,  0.0481,  0.0184,  0.0145, -0.0023,  0.0273,  0.0059,
    0.0521, -0.0008,  0.0113,  0.0046, -0.0235, -0.0034,  0.0579,  0.0184,
    -0.0023,  0.0039, -0.0583,  0.0043, -0.0375, -0.0431,  0.0239, -0.0121,
    0.0403,  0.0042, -0.0535, -0.0406, -0.0471,  0.0699, -0.0463,  0.0623,
    0.0008,  0.0375, -0.0126, -0.0791,  0.0389,  0.0056,  0.0332,  0.1193,
    0.0548,  0.0163,  0.0580,  0.0585, -0.0023,  0.0316,  0.0501,  0.0195,
    -0.0303, -0.0093, -0.0028, -0.0862, -0.0082,  0.0133,  0.0372, -0.0130,
    -0.0252, -0.0601,  0.0462,  0.0024,  0.0370,  0.0053,  0.0486,  0.0260,
    0.0529, -0.0222, -0.0337, -0.0378, -0.0240, -0.0204, -0.0463, -0.0604,
    -0.0065,  0.0256, -0.0231,  0.0547, -0.0271,  0.0394,  0.0178, -0.0114,
    -0.0236,  0.0065, -0.0244,  0.0418, -0.0355,  0.0294, -0.0756, -0.0156,
    -0.0311,  0.0522,  0.0308,  0.0253, -0.0088, -0.0177, -0.0579,  0.0169,
    -0.0578,  0.0451, -0.0149,  0.0350, -0.0065, -0.0326, -0.0319, -0.0203,
    0.0399, -0.0054,  0.0540,  0.0238,  0.0068, -0.0264, -0.0584, -0.0353,
    0.0533,  0.0097, -0.0130,  0.0273,  0.0593, -0.0142, -0.0164,  0.0768,
    -0.0137, -0.0242,  0.0391,  0.0240, -0.0510, -0.0480, -0.0212, -0.0555,
    -0.0447,  0.0110, -0.0289,  0.0292,  0.0189, -0.0042,  0.0404, -0.0355,
    -0.0287,  0.0466, -0.0058,  0.0455,  0.0385, -0.0281,  0.0082,  0.0254,
    0.0210, -0.0505, -0.0373,  0.0296, -0.0138, -0.0226,  0.0006, -0.0037,
    0.0854,  0.0174,  0.0470,  0.0274, -0.0403,  0.0616, -0.0645,  0.0339,
    0.0064, -0.0273,  0.0588, -0.0362, -0.0152,  0.0063,  0.0533, -0.0064,
    0.0300, -0.0677,  0.0363, -0.0024, -0.0018,  0.0415,  0.0177, -0.0714,
    0.0100, -0.0123,  0.0138,  0.0997, -0.0464,  0.0182,  0.0437,  0.0099,
    0.0213,  0.0126, -0.0155, -0.0325,  0.0282, -0.0085,  0.0005,  0.0223,
    0.0149,  0.0244,  0.0334,  0.0063, -0.0244, -0.0034, -0.0542, -0.0191,
    0.0082,  0.0261, -0.0061,  0.0381,  0.0217,  0.0314,  0.0609,  0.0676,
    -0.0526, -0.0318, -0.0179,  0.0121,  0.0529, -0.0194,  0.0306, -0.0761,
    -0.0310, -0.0301, -0.0156, -0.0147,  0.0856, -0.0261,  0.0704,  0.0267,
    -0.0292,  0.0201, -0.0641, -0.0625, -0.0215, -0.0317,  0.0290, -0.0032,
    0.0435, -0.0297, -0.0427, -0.0308,  0.0699,  0.0015,  0.0395,  0.0386,
    0.0194, -0.0397, -0.0633,  0.0269, -0.0100,  0.0301, -0.0271,  0.0031,
    -0.0283, -0.0179, -0.0106, -0.0797,  0.0407, -0.0361, -0.0474,  0.0076,
    -0.0113, -0.0241,  0.0154,  0.0324,  0.0334, -0.0052,  0.0022, -0.0029,
    0.0564,  0.0043, -0.0168,  0.0153, -0.0629, -0.0063, -0.0245,  0.0572,
    0.0379,  0.0272, -0.0473, -0.0104,  0.0201, -0.0021, -0.0012,  0.0767,
    -0.0321,  0.0166,  0.0471, -0.0443, -0.0796,  0.0152, -0.0118,  0.0293,
    0.0067,  0.0147,  0.0019,  0.0132,  0.0067, -0.0533, -0.0534,  0.0272,
    -0.0221,  0.0098, -0.0008, -0.0042,  0.0033,  0.0076,  0.0157, -0.0107,
    -0.0071,  0.0212,  0.0476,  0.0395,  0.0095, -0.0112, -0.0249,  0.0390,
    -0.0741,  0.0493, -0.0110, -0.0267,  0.0102,  0.0368, -0.0126,  0.0331,
    -0.0075,  0.0364, -0.0296, -0.0229,  0.0432, -0.0228, -0.0096, -0.0032,
    -0.0402,  0.0267,  0.0251,  0.0222, -0.0089,  0.0625, -0.0489, -0.0210,
    0.0754, -0.0495, -0.0294,  0.0205,  0.0480,  0.0321,  0.0090, -0.0083,
    0.0175, -0.0561,  0.0180, -0.0277, -0.0542, -0.0160, -0.0017,  0.0079,
    -0.0545,  0.0444, -0.0282, -0.0293, -0.0535, -0.0606,  0.0669,  0.0245,
    0.0384,  0.0245, -0.0596,  0.0227,  0.0386, -0.0639,  0.0236,  0.0377], '$2a$12$MoBE4eBLNBrM7G7bLg8We.AvVLINSh1YOS.YXPp3EEwYq/6eOTeD6', True) AS tmp
WHERE NOT EXISTS (
        SELECT user_id FROM users WHERE user_id = '0884831b-42f9-4f81-af88-3e777be69c8f'
    ) LIMIT 1;

INSERT INTO user_roles(user_id, role)
SELECT * FROM (SELECT '0884831b-42f9-4f81-af88-3e777be69c8f', 'ADMIN') AS tmp
WHERE NOT EXISTS (
        SELECT user_id, role FROM user_roles AS u WHERE u.user_id = '0884831b-42f9-4f81-af88-3e777be69c8f' AND u.role = 'ADMIN'
    ) LIMIT 1;