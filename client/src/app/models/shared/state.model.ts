import { DataStateEnum } from "./data-state.enum"


export type DataState<T> = LoadingState<T> | LoadedState<T> | ErrorState<T> | InitialState<T>;

export type LoadingState<T> = {
    data: never,
    state: DataStateEnum.LOADING
}

export type LoadedState<T> = {
    data: T,
    state: DataStateEnum.LOADED
}

export type ErrorState<T> = {
    data: never,
    state: DataStateEnum.ERROR
}

export type InitialState<T> = {
    data: never;
    state: DataStateEnum.INIT
}

export const initialDataState = <T>(): DataState<T> => {
    return { state: DataStateEnum.INIT } as DataState<T>
}

export const loadingDataState = <T>(): DataState<T> => {
    return { state: DataStateEnum.LOADING } as DataState<T>
}

export const loadedDataState = <T>(data: T): DataState<T> => {
    return { state: DataStateEnum.LOADED, data } as DataState<T>
}

export const errorDataState = <T>(): DataState<T> => {
    return { state: DataStateEnum.ERROR } as DataState<T>
}
