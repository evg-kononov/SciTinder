import {
    FETCH_SCIENTISTS_BY_NAME_CONTAINING,
    FETCH_SCIENTISTS_BY_NAME_CONTAINING_ERROR,
    FETCH_SCIENTISTS_BY_NAME_LIKE,
    FETCH_SCIENTISTS_BY_NAME_LIKE_ERROR,
    FETCH_SCIENTISTS_BY_SCOPUS_ID,
    FETCH_SCIENTISTS_BY_SCOPUS_ID_ERROR,
    FETCH_SIMILAR_BY_PROMPT,
    FETCH_SIMILAR_BY_PROMPT_ERROR
} from "../../actions/types";

export const menuItems = {
    'name':
        {
            index: 0,
            name: 'Name',
            withAuthorSelect: true,
            showInMenuBar: true,
            fetchScientistsAction: FETCH_SCIENTISTS_BY_NAME_LIKE,
            fetchScientistsErrorAction: FETCH_SCIENTISTS_BY_NAME_LIKE_ERROR
        },
    'scopusId':
        {
            index: 1,
            name: 'Scopus ID',
            withAuthorSelect: true,
            showInMenuBar: true,
            fetchScientistsAction: FETCH_SCIENTISTS_BY_SCOPUS_ID,
            fetchScientistsErrorAction: FETCH_SCIENTISTS_BY_SCOPUS_ID_ERROR
        },
    'fast':
        {
            index: 2,
            name: 'Fast',
            withAuthorSelect: true,
            showInMenuBar: true,
            fetchScientistsAction: FETCH_SCIENTISTS_BY_NAME_CONTAINING,
            fetchScientistsErrorAction: FETCH_SCIENTISTS_BY_NAME_CONTAINING_ERROR
        },
    'prompt':
        {
            index: 3,
            name: 'Prompt',
            withAuthorSelect: false,
            showInMenuBar: false,
            fetchScientistsAction: FETCH_SIMILAR_BY_PROMPT,
            fetchScientistsErrorAction: FETCH_SIMILAR_BY_PROMPT_ERROR
        }
};

export const headNamesAuthor = ['№', 'Name', 'Organization', 'h-Index', 'Link'];

export const headNamesSimilarity = ['№', 'Name', 'Organization', 'h-Index', 'Similarity', 'Link'];