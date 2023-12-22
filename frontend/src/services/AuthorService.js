import spring from "../apis/spring";
export const findByNameLike = (params) => {
    return spring.get('/author/findByNameLike', {params});
};

export const findByScopusId = (params) => {
    return spring.get('/author/findByScopusId', {params});
};

export const findByNameContaining = (params) => {
    return spring.get('/author/findByNameContaining', {params});
};