bb.hM = function (a, b) {
    return !a || "undefined" == typeof b || "s" == a && 11 != b && 7 != b && 2 != b && 26 != b && 28 != b || "nb" == a && 12 != b || "bd" == a && 21 != b || "bda" == a && 45 != b || "sa" == a && 46 != b || "nba" == a && 47 != b ? q : o
};
x.extend(bb.prototype, {
    Bz: function () {
        for (var a = 0, b = this.Pa.length; a < b; a++) {
            var c = this.Pa[a];
            this[c.method].apply(this, c.arguments)
        }
        delete this.Pa;
        this.ra()
    }, ra: function () {
        this.$m = new eh(this);
        this.Zm = new fh(this)
    }, search: function (a, b, c) {
        if (!a || a instanceof Array && (1 > a.length || 10 < a.length)) this.Oe(), this.sb(5), this.Sa(V.Sc);
        else if (b = b || {}, b.customData) this.Lz(), this.ns.search({
            tb: a,
            Kp: "local",
            Sw: b.customData
        }), this.Ol = o;
        else {
            this.Ol = q;
            var d = c || {},
                e = this;
            this.Jg(this.yd, function (c) {
                c = c || 1;
                e.Fc = e.DA(a);
                c = e.Fc ? {
                    qt: "sa",
                    c: c,
                    wd: a.join("$$"),
                    wdn: a.length,
                    rn: e.pf()
                } : {
                    qt: b.forceLocal ? "con" : "s",
                    c: c,
                    wd: a,
                    rn: e.pf()
                };
                e.k.Dp && x.extend(c, {
                    from: e.k.Dp
                });
                x.extend(c, d.Zc);
                b.log && (c.log = "center");
                var g = {
                    Zc: c,
                    options: b,
                    sd: d.sd || a
                };
                x.extend(g, d);
                dd.bb(function (a, b) {
                    e.Kf(a, b)
                }, c, g)
            })
        }
    }, DA: function (a) {
        return a && a instanceof Array ? o : q
    }, bn: function (a, b, c) {
        if (!a || !b || a instanceof Array && (1 > a.length || 10 < a.length)) this.Oe(), this.sb(5), this.Sa(V.Sc);
        else {
            var c = c || {},
                d = this;
            if (c.customData) this.Lz(), this.ns.search({
                tb: a,
                hb: b,
                Kp: "bound",
                Sw: c.customData
            }), this.Ol = o;
            else {
                this.Ol = q;
                var e = c || {};
                this.Jg(this.yd, function (c) {
                    var c = c || 1,
                        g = b.se(),
                        i = b.of(),
                        g = R.zb(g),
                        i = R.zb(i),
                        g = "(" + g.lng + "," + g.lat + ";" + i.lng + "," + i.lat + ")";
                    d.Fc = d.DA(a);
                    c = d.Fc ? {
                        qt: "bda",
                        c: c,
                        wd: a.join("$$"),
                        wdn: a.length,
                        ar: g,
                        rn: d.pf(),
                        l: 18
                    } : {
                        qt: "bd",
                        c: c,
                        wd: a,
                        ar: g,
                        rn: d.k.Ek,
                        l: 18
                    };
                    d.k.Dp && x.extend(c, {
                        from: d.k.Dp
                    });
                    x.extend(c, e.Zc);
                    dd.bb(function (a, b) {
                        d.Kf(a, b)
                    }, c, e && e.Ga && e.xa ? {
                        Zc: c,
                        hb: b,
                        Ga: e.Ga,
                        xa: e.xa,
                        sd: e.sd || a
                    } : {
                        Zc: c,
                        hb: b,
                        sd: e.sd || a
                    })
                })
            }
        }
    }, Jp: function (a, b, c, d) {
        if (!a || !b || !(b instanceof H) && "object" == typeof b && !b.uid || a instanceof Array && (1 > a.length || 10 < a.length)) this.Oe(), this.sb(5), this.Sa(V.Sc);
        else if (d = d || {}, d.customData) this.Lz(), this.ns.search({
            tb: a,
            Ga: b,
            xa: c,
            Kp: "nearby",
            Sw: d.customData
        }), this.Ol = o;
        else {
            this.Ol = q;
            var c = c || bb.kG,
                c = 0 > c ? bb.kG : c > bb.rG ? bb.rG : c,
                e = this,
                f = d || {};
            if (b instanceof H) {
                var g = R.zb(b),
                    d = new H(g.lng - c, g.lat - c),
                    g = new H(g.lng + c, g.lat + c),
                    d = R.Ab(d),
                    g = R.Ab(g),
                    d = new eb(new H(d.lng, d.lat), new H(g.lng, g.lat));
                f.Ga = b;
                f.xa = c;
                this.bn(a, d, f)
            } else this.Jg(this.yd, function (d) {
                d = d || 1;
                e.Fc = e.DA(a);
                d = e.Fc ? {
                    qt: "nba",
                    c: d,
                    wd: a.join("$$"),
                    wdn: a.length,
                    rn: e.pf(),
                    uid: b.uid,
                    r: c
                } : {
                    qt: "nb",
                    c: d,
                    wd: a,
                    rn: e.pf(),
                    uid: b.uid,
                    r: c
                };
                if ("string" == typeof b) {
                    if (e.Fc) {
                        d.qt = "sa";
                        for (var g = [], l = 0; l < a.length; l++) g.push(b + " " + a[l]);
                        d.wd = g.join("$$");
                        d.wdn = g.length
                    } else d.qt = "s", d.wd = b + " " + a;
                    delete d.r;
                    delete d.uid
                }
                e.k.Dp && x.extend(d, {
                    from: e.k.Dp
                });
                x.extend(d, f.Zc);
                dd.bb(function (a, b) {
                    e.Kf(a, b)
                }, d, {
                    Zc: d,
                    Ga: b,
                    xa: c,
                    sd: f.sd || a
                })
            })
        }
    }, Kf: function (a, b) {
        var c = this;
        c.Fc = 45 == a.result.type || 46 == a.result.type || 47 == a.result.type ? o : q;
        this.Oe();
        c.Fa = a;
        this.ga = b;
        var d = a.result,
            e = b.Zc.qt;
        if (c.Fc) {
            if (0 != d.error || !bb.hM(e, d.type)) {
                i = a.current_city;
                this.Ii = [];
                for (var d = b.sd, e = 0, f = d.length; e < f; e++) {
                    var g = new gh({
                        tb: d[e],
                        city: i.name,
                        province: i.up_province_name || "",
                        Dw: i.code,
                        zp: c.pf(),
                        Ga: b.Ga,
                        xa: b.xa,
                        hb: b.Ga && b.xa ? j : b.hb,
                        ri: ""
                    });
                    g.iB = b.Zc.qt;
                    this.Ii.push(g)
                }
                this.sb(2);
                this.Sa(V.Sc, this.Ii);
                c.Zm.dispatchEvent("render");
                return
            }
        } else if (0 != d.error || !bb.hM(e, d.type)) {
            var i = a.current_city;
            this.ja = new gh({
                tb: b.sd,
                city: i.name,
                province: i.up_province_name || "",
                Dw: i.code,
                zp: c.pf(),
                Ga: b.Ga,
                xa: b.xa,
                hb: b.Ga && b.xa ? j : b.hb,
                ri: this.fv(b, i)
            });
            this.ja.iB = b.Zc.qt;
            this.sb(2);
            this.Sa(V.Sc, this.ja);
            c.Zm.dispatchEvent("render");
            return
        }
        c.Dl(b);
        c.$m.dispatchEvent("render");
        c.Zm.dispatchEvent("render");
        (d = this.ga.Ga) && ("string" != typeof d && !(d instanceof H)) && this.$m.nw(d);
        d = {};
        switch (this.Fa.result && this.Fa.result.type) {
        case 11:
        case 12:
        case 21:
        case 45:
        case 46:
        case 47:
            d.ia = this.pz;
            break;
        case 2:
            d.Ga = this.ja.wk(0).point;
            d.ug = this.Fa.content.level;
            break;
        case 28:
            d.Ga = this.ja.wk(0).point;
            d.ug = 13;
            break;
        case 26:
            d.Ga = this.ja.wk(0).point, d.ug = parseInt(this.Fa.content[1])
        }
        this.$m.iL(d);
        d = this.Fc ? this.fA() : this.ja;
        this.k.ka.Nt && (d && 0 < d.Ws()) && (c = this, setTimeout(function () {
            c.select(0)
        }, 240))
    }, Dl: function (a) {
        var b = this.Fa.result,
            c = this.Fa.content,
            d = this.Fa.current_city,
            e = b.type,
            f = b.page_num || 0,
            g = 0,
            i = 0,
            k = [],
            l = [],
            m = [],
            n = [],
            t = [],
            v = [];
        if (this.Fa.psrs && this.Fa.psrs.SEResult) {
            var w = this.Fa.psrs.SEResult;
            if (0 < w.length)
                for (var y = 0, C = w.length; y < C; ++y) v.push(w[y])
        }
        if (w = this.Fa.suggest_query) {
            y = 0;
            for (C = w.length; y < C; ++y) w[y] && w[y].query && v.push(w[y].query)
        }
        v = P.unique(v);
        if (7 != e && 26 != e) {
            var A;
            this.Fc || (i = b.count - (0 == f ? b.spec_dispnum || 0 : 0), g = 760 > b.total ? b.total : 760, A = 0 == f ? b.spec_dispnum || 0 : 0);
            if (2 != e)
                if (this.Fc) {
                    C = b.result_array;
                    f = a.sd.length;
                    for (y = 0; y < f; y++)
                        if (n[y] = [], t[y] = [], m[y] = [], C[y])
                            if (7 == C[y].type) {
                                if (c && c[y])
                                    for (A = 0; A < c[y].length; A++) w = c[y][A], m[y].push({
                                        city: w.name,
                                        Iz: w.code,
                                        numResults: w.num
                                    });
                                if (this.Fa.more_city && this.Fa.more_city[y]) {
                                    var D = this.Fa.more_city[y];
                                    A = 0;
                                    for (var B = D.length; A < B; A++)
                                        for (var F = 0, J = D[A].city.length; F < J; F++) w = D[A].city[F], m[y].push({
                                            city: w.name,
                                            Iz: w.code,
                                            numResults: w.num
                                        })
                                }
                            } else {
                                w = C[y].count;
                                t[y].push({
                                    count: w,
                                    ZY: C[y].page_num || 0,
                                    total: 760 < C[y].total ? 760 : C[y].total
                                });
                                for (A = 0; A < w; A++)
                                    if (c && (c[y] && c[y][A]) && (B = c[y][A], B.addr !== p)) {
                                        F = this.pH(B.addr, B.poiType);
                                        D = [];
                                        if (B.cla && 0 < B.cla.length)
                                            for (J = B.cla.length; J--;) L = B.cla[J][1], L = L.replace("<b>", "").replace("</b>", ""), D.unshift(L);
                                        B = {
                                            title: B.name,
                                            uid: B.uid,
                                            point: P.vb(B.geo, o).point,
                                            url: V.Nh(B.uid, d.code),
                                            detailUrl: "http://api.map.baidu.com/place/detail?uid=" + B.uid + "&output=html&source=jsapi",
                                            address: F,
                                            city: d.name,
                                            province: d.up_province_name || "",
                                            phoneNumber: B.tel,
                                            postcode: B.zip,
                                            type: B.poiType || 0,
                                            isAccurate: B.acc_flag && 1 == B.acc_flag ? o : q,
                                            tags: D
                                        };
                                        n[y].push(B);
                                        l.push(B.point);
                                        this.wf.push(B)
                                    }
                            }
                } else
                    for (y = A; y < b.count; y++) {
                        if (c && c[y] && (B = c[y], B.addr !== p)) {
                            F = this.pH(B.addr, B.poiType);
                            D = [];
                            if (B.cla && 0 < B.cla.length)
                                for (J = B.cla.length; J--;) {
                                    var L = B.cla[J][1],
                                        L = L.replace("<b>", "").replace("</b>", "");
                                    D.unshift(L)
                                }
                            B = {
                                title: B.name,
                                uid: B.uid,
                                point: P.vb(B.geo, o).point,
                                url: V.Nh(B.uid, d.code),
                                detailUrl: "http://api.map.baidu.com/place/detail?uid=" + B.uid + "&output=html&source=jsapi",
                                address: F,
                                city: d.name,
                                province: d.up_province_name || "",
                                phoneNumber: B.tel,
                                postcode: B.zip,
                                type: B.poiType || 0,
                                isAccurate: B.acc_flag && 1 == B.acc_flag ? o : q
                            };
                            0 < D.length && (B.tags = D);
                            k.push(B);
                            l.push(B.point);
                            this.wf.push(B)
                        }
                    } else i = g = 1, B = {
                        title: c.cname,
                        uid: c.uid,
                        point: P.vb(c.geo, o).point,
                        address: c.cname,
                        url: z.up + "?s=" + encodeURIComponent("s&wd=" + b.wd)
                    }, k.push(B), this.wf.push(B), l.push(B.point)
        } else if (26 == e && (i = g = 1, B = {
            title: b.wd,
            point: P.dZ(c[0]),
            url: z.up + "?s=" + encodeURIComponent("s&wd=" + b.wd)
        }, k.push(B), this.wf.push(B), l.push(B.point)), 7 == e) {
            for (y = i = g = 0; y < c.length; y++) m.push({
                city: c[y].name,
                Iz: c[y].code,
                numResults: c[y].num
            });
            if (this.Fa.more_city) {
                D = this.Fa.more_city;
                y = 0;
                for (B = D.length; y < B; y++) {
                    A = 0;
                    for (J = D[y].city.length; A < J; A++) c = D[y].city[A], m.push({
                        city: c.name,
                        Iz: c.code,
                        numResults: c.num
                    })
                }
            }
        }
        this.pz = l;
        if (this.Fc) {
            this.Ii = [];
            f = a.sd.length;
            b = o;
            for (y = 0; y < f; y++) 0 < t[y].length && 0 < t[y][0].total && (b = q), k = new gh({
                tb: a.sd[y] || "",
                count: 0 < t[y].length ? t[y][0].count : 0,
                total: 0 < t[y].length ? t[y][0].total : 0,
                NE: 0 < t[y].length ? t[y][0].ZY : 0,
                city: d.name,
                province: d.up_province_name || "",
                Dw: d.code,
                Fk: n[y] || [],
                eC: m[y] || [],
                zp: this.pf(),
                Ga: a.Ga,
                xa: a.xa,
                hb: a.Ga && a.xa ? j : a.hb,
                ri: "",
                kO: v
            }), k.iB = a.Zc.qt, this.Ii.push(k);
            b ? this.sb(2) : this.sb(0);
            this.Sa(V.Sc, this.Ii)
        } else this.ja = new gh({
            tb: a.sd,
            count: i,
            total: g,
            NE: b.page_num,
            city: d.name,
            province: d.up_province_name || "",
            Dw: d.code,
            Fk: k,
            eC: m,
            zp: this.pf(),
            Ga: a.Ga,
            xa: a.xa,
            hb: a.Ga && a.xa ? j : a.hb,
            ri: this.fv(a, d),
            kO: v
        }), this.ja.iB = a.Zc.qt, 0 == g && 7 != e ? this.sb(2) : 7 != e ? this.sb(0) : this.sb(1), this.Sa(V.Sc, this.ja)
    }, fv: function (a, b) {
        var c = "",
            c = "string" == typeof a.Ga ? a.Ga + " " + a.sd : a.sd,
            d = b.name,
            e, f, g;
        "object" == typeof a.Ga && !(a.Ga instanceof H) && (f = a.Ga.point, e = a.xa);
        a.hb && !a.Ga && (g = a.hb);
        a.Ga && a.Ga instanceof H && (f = a.Ga, e = a.xa);
        c = z.wc + "place/search?res=jsapi&query=" + c + "&region=" + d + "&output=html";
        f && (c += "&location=" + f.lat + "," + f.lng + "&radius=" + e);
        g && (e = g.se(), g = g.of(), c += "&bounds=" + e.lat + "," + e.lng + "," + g.lat + "," + g.lng);
        return c
    }, pH: function (a, b) {
        b = b || 0;
        return 1 == b || 3 == b ? P.unique(a.split(";")).join("; ") : a
    }, G0: function (a) {
        for (var b = 0, c = a.length; b < c; b++)
            if (0 < a[b].oL()) return o;
        return q
    }, Kj: function () {
        for (var a = 0, b = this.wa.length; a < b; a++) this.wa[a].remove(), this.wa[a] = p;
        a = this.wa.length = 0;
        for (b = this.wf.length; a < b; a++) this.wf[a] = p;
        this.wf.length = 0;
        this.Az && (this.Az.remove(), this.Az = p)
    }, fA: function () {
        if (!this.Fc) return this.ja;
        for (var a, b = -1, c = 0, d = this.Ii.length; c < d; c++) {
            var e = this.Ii[c];
            e.ap() > b && (a = e, b = e.ap())
        }
        return a
    }, Oe: function () {
        delete this.Fa;
        delete this.Bd;
        delete this.ja;
        this.Fc && delete this.Ii;
        delete this.ga;
        this.kb = -1;
        this.sb();
        this.Kj();
        this.k.ka.Ha && (this.k.ka.Ha.innerHTML = "")
    }, Bm: function (a) {
        if (this.Ol) this.ns.Bm(a);
        else if (this.ga) {
            var b;
            b = this.Fc ? this.fA() : this.ja;
            if ("number" == typeof a && !isNaN(a) && 0 <= a && a <= b.ap() - 1) switch (this.ga.Zc.pn = a, this.ga.Zc.qt) {
            case "s":
            case "con":
                this.search(this.ga.Zc.wd, this.ga.options, this.ga);
                break;
            case "bd":
                this.bn(this.ga.Zc.wd, this.ga.hb, this.ga);
                break;
            case "nb":
                this.Jp(this.ga.Zc.wd, this.ga.Ga, this.ga.xa, this.ga);
                break;
            case "bda":
                this.bn(this.ga.sd, this.ga.hb, this.ga);
                break;
            case "sa":
                this.search(this.ga.sd, this.ga.options, this.ga);
                break;
            case "nba":
                this.Jp(this.ga.sd, this.ga.Ga, this.ga.xa, this.ga)
            } else this.sb(5), this.Sa(V.Sc)
        }
    }, Lz: function () {
        this.ns || (this.ns = new hh(this))
    }, select: function (a) {
        this.$m.select(a);
        this.Zm.select(a);
        this.kb = a
    }
});
S(vd, {
    gotoPage: vd.Bm,
    searchNearby: vd.Jp,
    searchInBounds: vd.bn,
    search: vd.search,
    clearResults: vd.Oe
});

function gh(a) {
    this.keyword = a.tb || "";
    this.pT = a.NE || 0;
    this.$Q = a.count || 0;
    this.WA = a.total || 0;
    this.gT = Math.ceil(a.total / a.zp);
    this.center = a.Ga;
    this.radius = a.xa;
    this.bounds = a.hb;
    this.city = a.city;
    this.province = a.province;
    this.viewport = a.viewport;
    this.moreResultsUrl = a.ri;
    this.yr = a.Fk && a.Fk.slice(0) || [];
    this.wQ = a.eC && a.eC.slice(0);
    this.suggestions = a.kO || []
}
x.extend(gh.prototype, {
    wk: function (a) {
        if (this.yr[a]) return this.yr[a]
    }, Ws: u("WA"),
    ap: u("gT"),
    oL: u("$Q"),
    vL: u("pT"),
    hx: u("wQ"),
    toString: ca("LocalResult")
});
var ih = gh.prototype;
S(ih, {
    getPoi: ih.wk,
    getCurrentNumPois: ih.oL,
    getNumPois: ih.Ws,
    getNumPages: ih.ap,
    getPageIndex: ih.vL,
    getCityList: ih.hx
});

function hh(a) {
    x.lang.Ca.call(this);
    this.na = a;
    this.na.kb = -1
}
x.lang.ua(hh, x.lang.Ca, "CustomSearch");
x.extend(hh.prototype, {
    search: function (a) {
        this.Kp = a.Kp;
        this.na.Fc = q;
        if (this.Bz(a)) {
            var b = this;
            this.na.Jg(this.na.yd, function (c) {
                a.region = c;
                c = b.gS(a);
                b.NN(c, {
                    Zc: c,
                    options: a
                })
            })
        }
    }, Bz: function () {
        var a = o;
        qa || (this.na.sb(4), this.na.Sa(V.Sc), a = q);
        return a
    }, gS: function (a) {
        var b = this.Sw = a.Sw;
        b && b.geotableId && (this.nt = o);
        var c = {
            region: a.region,
            page_size: this.na.pf(),
            ak: qa
        };
        a.YY && (c.page_index = a.YY);
        a.tb && (c.q = a.tb);
        if (a.hb) {
            var d = a.hb.se(),
                e = a.hb.of();
            c.bounds = this.nt ? d.lng + "," + d.lat + ";" + e.lng + "," + e.lat : d.lat + "," + d.lng + ";" + e.lat + "," + e.lng
        }
        if (a.Ga && (a.Ga instanceof H || a.Ga.point instanceof H)) d = p, a.Ga instanceof H ? d = a.Ga : a.Ga.point instanceof H && (d = a.Ga.point), c.location = this.nt ? d.lng + "," + d.lat : d.lat + "," + d.lng;
        "string" == typeof a.Ga && (c.q = a.Ga + " " + a.tb, this.Kp = "local");
        a.xa && (c.radius = a.xa);
        this.nt ? c.geotable_id = b.geotableId : b && b.databoxId && (c.filter = "databox:" + b.databoxId);
        b.tags && (c.tags = b.tags);
        b.filter && (c.filter = b.filter);
        return c
    }, NN: function (a, b) {
        var c = this,
            d = "geosearch/poi";
        this.nt && (d = "geosearch/v2/" + this.Kp);
        dd.bb(function (a, b) {
            c.Kf(a, b)
        }, a, b, d)
    }, Kf: function (a, b) {
        this.na.Oe();
        this.na.ga = b;
        if (0 != a.status) this.ja = new gh({
            tb: b.options.tb,
            zp: this.na.pf(),
            Ga: b.options.Ga || "",
            xa: b.options.xa || "",
            hb: b.options.hb || "",
            ri: ""
        }), 5 == a.status ? this.na.sb(4) : this.na.sb(2), this.na.ja = this.ja, this.na.Sa(V.Sc, this.ja), this.na.Zm.dispatchEvent("render");
        else {
            this.Dl(a, b);
            this.na.$m.dispatchEvent("render");
            this.na.Zm.dispatchEvent("render");
            var c = b.options.Ga;
            c && ("string" != typeof c && !(c instanceof H) && 0 == this.na.wm()) && this.na.$m.nw(c);
            c = {};
            c.ia = this.na.pz;
            this.na.$m.iL(c);
            c = this.na.ja;
            if (this.na.k.ka.Nt && c && 0 < c.Ws()) {
                var d = this;
                setTimeout(function () {
                    d.na.select(0)
                }, 240)
            }
        }
    }, Dl: function (a, b) {
        var c = a.content || {},
            d = [],
            e = [],
            f = this.nt;
        f && (c = a.contents);
        for (var g = 0, i = a.size; g < i && c[g]; g++) {
            var k = c[g],
                l = f ? new H(k.location[0], k.location[1]) : new H(k.longitude, k.latitude);
            d.push({
                title: f ? k.title : k.name,
                uid: k.uid,
                point: l,
                url: "",
                address: f ? k.address : k.addr,
                city: k.city,
                province: k.province,
                phoneNumber: k.tel,
                postcode: k.zip,
                type: k.cla
            });
            e.push(l)
        }
        this.na.wf = d;
        this.na.pz = e;
        this.ja = new gh({
            tb: b.options.tb,
            count: a.size,
            total: a.total,
            NE: b.Zc.page_index,
            city: "",
            province: "",
            Dw: "",
            Fk: d,
            zp: this.na.pf(),
            Ga: b.options.Ga,
            xa: b.options.xa,
            hb: b.options.hb,
            ri: ""
        });
        0 == a.total ? this.na.sb(2) : this.na.sb(0);
        this.na.ja = this.ja;
        this.na.Sa(V.Sc, this.ja)
    }, Bm: function (a) {
        var b = this.na.ja;
        "number" == typeof a && !isNaN(a) && 0 <= a && a <= b.ap() - 1 ? (this.na.ga.Zc.page_index = a, this.NN(this.na.ga.Zc, this.na.ga)) : (this.na.sb(5), this.na.Sa(V.Sc))
    }
});

function eh(a) {
    x.lang.Ca.call(this);
    this.na = a;
    this.map = a.k.ka.map;
    this.wa = a.wa;
    this.ra()
}
x.lang.ua(eh, x.lang.Ca, "RenderMap");
x.extend(eh.prototype, {
    ra: function () {
        this.addEventListener("render", this.Aa)
    }, Aa: function () {
        if (this.map) {
            for (var a = this, b = this.na.wf, c = [], d = 0, e = b.length; d < e; d++) {
                var f = b[d];
                c.push(f.point);
                var g = this.TP(f.point, d, f.title);
                g && (f.marker = g, function () {
                    var b = d;
                    g.addEventListener("click", function () {
                        a.na.select(b)
                    })
                }(), this.wa.push(g))
            }
            this.na.Sa(V.$p, b)
        }
    }, nw: function (a) {
        var b = this;
        if (a && "string" != typeof a && !(a instanceof H)) {
            var c = this.na.Az = U.nw(this.map, a.point, a.title);
            c.addEventListener("click", function () {
                b.na.select(-1);
                b.na.kb = "c";
                var d = b.cH(a);
                c.Nb(d)
            })
        }
    }, TP: function (a, b, c) {
        return this.na.k.Ek <= bb.cq && !this.na.Fc ? U.PU(this.map, a, b, c) : U.OU(this.map, a, c)
    }, select: function (a) {
        if (this.map && -1 < a && this.wa[a]) {
            if (G())
                for (var b = p, c = 0, d = this.wa.length; c < d; c++) b = this.wa[c], a == c ? b.Ub(b.UL) : b.Ub(b.cE), b.draw;
            else {
                c = 0;
                for (d = this.wa.length; c < d; c++) b = this.wa[c], b.yi(q)
            }
            this.map.Wc();
            if (b = this.na.wf[a]) b = this.cH(b), a = this.wa[a], a.yi(o), a.Nb(b)
        }
    }, cH: function (a) {
        var b = U.TV({
                title: a.title,
                Yr: a.address,
                z_: a.phoneNumber,
                url: a.url,
                dW: a.detailUrl,
                uid: a.uid,
                TE: a.type
            }),
            c = this;
        b.addEventListener("close", function () {
            c.na.Zm.AV()
        });
        b.addEventListener("open", function () {
            c.na.Sa(V.Bn, a, U.Ts(c.map))
        });
        return b
    }, iL: function (a) {
        var b = this.na.k.ka;
        if (this.map)
            if (a.ia) {
                var c = !b.Xg,
                    b = !b.Nt && b.Xg,
                    a = this.map.at(a.ia, {
                        margins: [30, 30, 30, 30]
                    });
                c || this.map.Ah(a, {
                    enableAnimation: b
                })
            } else a.ug = P.dx(a.ug, this.map), b.Xg && this.map.Dd(a.Ga, a.ug)
    }
});

function fh(a) {
    x.lang.Ca.call(this);
    this.na = a;
    this.Ha = a.k.ka.Ha;
    this.ra()
}
x.lang.ua(fh, x.lang.Ca, "RenderList");
x.extend(fh.prototype, {
    ra: function () {
        this.addEventListener("render", this.Aa)
    }, Aa: function () {
        if (this.Ha) {
            var a = M("div", {
                    style: "font:12px " + E.fontFamily + ";border:1px solid #999;"
                }),
                b = M("div", {
                    style: "background:#fff"
                }),
                c = M("ol", {
                    style: "list-style:none;padding:0;margin:0"
                }),
                d = this.na.wm(),
                e = p;
            if (0 == d)
                for (var e = 0, f = this.na.wf.length; e < f; e++) d = this.OQ(e), c.appendChild(d);
            else if (1 == d) {
                if (1 == d && !this.na.Fc) {
                    e = 0;
                    for (f = Math.min(6, this.na.ja.hx().length); e < f; e++) d = this.MQ(e), c.appendChild(d)
                }
            } else {
                e = "";
                switch (d) {
                case 2:
                    e = "\u62b1\u6b49\uff0c\u672a\u627e\u5230\u76f8\u5173\u5730\u70b9\u3002";
                    break;
                case 4:
                    e = "\u62b1\u6b49\uff0c\u60a8\u6240\u63d0\u4f9b\u7684key\u65e0\u6548\u3002"
                }
                d = M("li", {
                    style: "margin:2px 0;padding:0 5px 0 3px;overflow:hidden;line-height:17px"
                });
                d.innerHTML = e;
                c.appendChild(d)
            }
            b.appendChild(c);
            a.appendChild(b);
            e = this.UQ();
            a.appendChild(e);
            this.Ha.appendChild(a);
            this.na.Sa(V.zu, a)
        }
    }, OQ: function (a) {
        var b = this.na.wf;
        if (b && b[a]) {
            var b = b[a],
                c = M("li", {
                    style: "margin:2px 0;padding:0 5px 5px 0px;cursor:pointer;overflow:hidden;line-height:17px;*zoom:1;"
                }); - 1 < this.na.kb && a == this.na.kb && (c.style.backgroundColor = "#f0f0f0");
            var d = 0 == a ? "0px" : "-" + 25 * a + "px";
            if (this.na.k.Ek > bb.cq || this.na.Fc) d = "-275px";
            var d = ["<span style='background:url(" + z.wc + "images/markers.png) -23px " + d + " no-repeat;width:19px;height:25px;cursor:pointer;float:left;*zoom:1;overflow:hidden;margin:2px 3px 0 5px;*margin-right:0px;display:inline;'>&nbsp;</span>"],
                e, f = RegExp(this.na.Fc ? this.na.ga.sd.join("|") : this.na.ja.keyword, "ig");
            b.title && (e = b.title.replace(f, "<b>$&</b>"));
            1 == b.type ? e += "-\u516c\u4ea4\u8f66\u7ad9" : 3 == b.type && (e += "-\u5730\u94c1\u7ad9");
            d.push("<div style='zoom:1;overflow:hidden;padding:0 5px;'>");
            d.push("<div style='line-height:20px;font-size:12px;'><span style='color:#00c;'>" + e + "</span>");
            b.detailUrl && d.push("<a target='_blank' href='" + b.detailUrl + "' style='margin-left:5px;font-size:12px;color:#3d6dcc;font-weight:normal;text-decoration:none;'>\u8be6\u60c5&raquo;</a>");
            d.push("</div>");
            b.address && (e = b.address.replace(f, "<b>$&</b>"), d.push("<div style='padding:2px 0;line-height:18px;*zoom:1;overflow:hidden;'><b style='float:left;font-weight:bold;*zoom:1;overflow:hidden;padding-right:5px;*margin-right:-3px;'>\u5730\u5740:</b><span style='color:#666;display:block;zoom:1;overflow:hidden;'>" + e + "</span></div>"));
            b.phoneNumber && d.push("<div style='padding:2px 0;line-height:18px;*zoom:1;overflow:hidden;'><b style='float:left;font-weight:bold;*zoom:1;overflow:hidden;padding-right:5px;*margin-right:-3px;'>\u7535\u8bdd:</b><span style='color:#666;'>" + b.phoneNumber + "</span></div>");
            d.push("</div>");
            c.innerHTML = d.join("");
            var g = this;
            c.onclick = function () {
                g.na.select(a)
            };
            return c
        }
    }, UQ: function () {
        var a = this,
            b = M("div", {
                style: "white-space:nowrap;text-align:right;background:#e5ecf9;margin-top:5px;padding:2px;overflow:hidden;zoom:1;"
            });
        if (!this.na.Fc && !this.na.Ol) {
            var c = M("a", {
                style: "color:#7777cc;float:right;",
                href: this.na.ja.moreResultsUrl,
                target: "_blank",
                title: "\u5230\u767e\u5ea6\u5730\u56fe\u67e5\u770b\u66f4\u591a\u7ed3\u679c"
            });
            c.innerHTML = "\u66f4\u591a\u7ed3\u679c&#187;";
            b.appendChild(c)
        }
        var c = M("div", {
                style: "float:left;margin-right:5px"
            }),
            d = this.na.fA();
        0 < this.na.wf.length && new ud(c, function (b) {
            a.na.Bm(b - 1)
        }, {
            Jd: d.ap(),
            page: d.vL() + 1,
            update: q
        });
        b.appendChild(c);
        return b
    }, AV: function () {
        this.Ha && ("number" == typeof this.na.kb && -1 != this.na.kb && this.Ha.getElementsByTagName("li")[this.na.kb]) && (this.Ha.getElementsByTagName("li")[this.na.kb].childNodes[1].style.backgroundColor = "");
        this.na.kb = -1
    }, select: function (a) {
        if (this.Ha) {
            var b = this.na.kb;
            "number" == typeof b && (-1 != b && this.Ha.getElementsByTagName("li")[b]) && (this.Ha.getElementsByTagName("li")[b].childNodes[1].style.backgroundColor = "");
            "number" == typeof a && (-1 != a && this.Ha.getElementsByTagName("li")[a]) && (this.Ha.getElementsByTagName("li")[a].childNodes[1].style.backgroundColor = "#f0f0f0")
        }
    }, MQ: function (a) {
        var b = M("li", {
            style: "margin:2px 0;padding:0 5px 0 3px;cursor:pointer;overflow:hidden;line-height:17px"
        });
        b.Sj = a;
        var c = this.na.ja.hx();
        b.innerHTML = "<span style='color:#00c;text-decoration:underline'>" + c[a].city + "</span> <span style='color:#666'>(\u5171" + c[a].numResults + "\u6761\u7ed3\u679c)</span>";
        var d = this.na,
            e = d.ja;
        b.onclick = function () {
            d.gn(e.hx()[a].city);
            d.search(e.keyword)
        };
        return b
    }
});