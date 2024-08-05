import http from "@/lib/axios";

export type Response = {
    result: any;
};

export async function hello() {
    return await http
        .get(`/greet/hello`)
        .then((res) => {
            return res.data as Response
        })
        .catch(() => {
            return null;
        });
}

export async function whoami() {
    return await http
        .get(`/greet/who-am-i`)
        .then((res) => {
            return res.data as Response
        })
        .catch(() => {
            return null;
        });
}