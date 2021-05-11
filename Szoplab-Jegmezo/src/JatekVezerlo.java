import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A Jatekvezerlo interfesz felellosegeit valositja meg.
 * A jatekvezerleset vegzi (pl. parancsok feldolgozasa)
 */
public interface JatekVezerlo {

    /**
     * Bemeneti faljbol ide olvassa be a parancsokat.
     */
    ArrayList<String> fparancsok = new ArrayList<>();

    /**
     * A felhasznalo altal megadott parancsok feldolgozasa
     * ezen fuggveny segitsegevel tortennek meg.
     *
     * @param p A parancs.
     * @param j A jatek maga, melyben parancsokat adunk ki.
     * @return boolean Attol fugguen, hogy a parancs feldolgozasa
     * sikerese volt-e terunk vissza egy ertekkel.
     */
    default boolean parancsfeldolgozas(String p, Jatek j) {
        boolean siker;
        String reszei[] = p.split(" ");

        /* Azonositja a parancsot a beolvasott szoveg elso resze alapjan.  */
        switch (reszei[0]) {
            /* Jatek inditasanak sikeressege */
            case "ujJatek":
                j.setMezo(new Mezo());
                kiir("jatek_letrehozasa_sikeres", "output.txt");
                siker = true;
                break;

            /*Tabla hozzaadasa a jatekhoz */
            case "hozzaadTabla":
                siker = hozzaadTabla(reszei, j);
                break;
            /*A kapcsolat letrejotte */
            case "kapcsolat":
                siker = kapcsolat(reszei, j);
                break;
            /* A karakter hozzaadasa */
            case "hozzaadKarakter":
                siker = hozzaadKarakter(reszei, j);

                break;
            /* A jatek  betoltese */
            case "betolt":
                siker = betolt(reszei, j);
                break;
            /* A jatek mentese */
            case "mentes":
                siker = mentes(reszei, j);
                break;
            /* Karakter kilepett */
            case "kilep":
                siker = kilep(reszei, j);
                break;
            /*Karakter kep*/
            case "lep":
                siker = lep(reszei, j);

                break;
            /* Karakter hasznalta valamelyik targyat */
            case "hasznal":
                siker = hasznal(reszei, j);

                break;
            /*Karakter valt a targyai kozott */
            case "valt":
                siker = valt(reszei, j);
                break;
            /*Karakter as */
            case "as":
                siker = as(reszei, j);
                break;
            /*Karakter hasznalja a kepesseget */
            case "kepesseghasznal":
                siker = kepesseghasznal(reszei, j);
                break;
            /*Karakter felvesz egy targyat */
            case "felvesz":
                siker = felvesz(reszei, j);
                break;
            /*Hovihar szakitja meg a jatekot */
            case "hovihar":
                siker = hovihar(reszei, j);
                break;
            /*Jegesmedve hozzaad */
            case "hozzaadNpc":
                siker = hozzaadNpc(reszei, j);
                break;
            /*Jegesmedve lep */
            case "lepNpc":
                siker = lepNpc(reszei, j);
                break;
            /*epitmeny (iglu, sator) hozzaaadva a jatekhoz */
            case "hozzaadEpitmeny":
                siker = hozzaadEpitmeny(reszei, j);
                break;

            case "epitmenyFrissit":
                siker = epitmenyFrissit(reszei, j);
                break;

            default:
                error("Ilyen parancs nincsen.");
                return false;
        }
        return siker;
    }

    //////////////////////////   ParancsFeldolgozast segito fuggvenyek   //////////////////////////////////////////

    /**
     * Tablat adunk az aktualis jatekoz.
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return
     */
    default boolean hozzaadTabla(String[] reszei, Jatek j) {
        /*adott tabla tipusa */
        int tipus;
        /*tabla stabilitasa*/
        int stabilitas;
        /*dolgok szama egy tablan */
        int dolog;
        /*horeteg merete */
        int horeteg;
        /*Ellenorzi hogy a megadott parameterek szama megfelel-e*/
        if (reszei.length == 5) {
            /*beallitja oket */
            tipus = Integer.parseInt(reszei[1]);
            stabilitas = Integer.parseInt(reszei[2]);
            dolog = Integer.parseInt(reszei[3]);
            horeteg = Integer.parseInt(reszei[4]);
        } else {
            kiir("tabla_hozzaadasa_sikertelen", "output.txt");
            return false;
        }

        if (reszei.length != 5) {
            kiir("tabla_hozzaadasa_sikertelen", "output.txt");
            return false;
            /*a tabla lehet viz, stabil vagy instabil (=3) */
        } else if (tipus > 3 || tipus < 1) {
            kiir("tabla_hozzaadasa_sikertelen", "output.txt");
            return false;
            /*stabilitasnak 0 es 10 kozott kell lennie */
        } else if (stabilitas < 0 || stabilitas > 10) {
            kiir("tabla_hozzaadasa_sikertelen", "output.txt");
            return false;
            /*dolgok szamanak 0 es 7 kozott kell lennie  */
        } else if (dolog < 0 || dolog > 7) {
            kiir("tabla_hozzaadasa_sikertelen", "output.txt");
            return false;
            /*horetegnek 0 es 3 kozott kell lennie */
        } else if (horeteg < 0 || horeteg > 3) {
            kiir("tabla_hozzaadasa_sikertelen", "output.txt");
            return false;
        } else {
            Tabla t;
            /*Megadott parameter szerint kivalasztja a tabla tipusat*/
            switch (tipus) {
                case 2:
                    t = new InstabilTabla();
                    break;
                case 3:
                    t = new Viz();
                    break;
                default:
                    t = new Tabla();
            }
            /*Beallitja a megadott stabilitast*/
            t.setStabilitas(stabilitas);
            Dolog d;
            /*Letrehozza a parameterkent megadott targyat*/
            switch (dolog) {
                case 1:
                    d = new Buvarruha();
                    break;
                case 2:
                    d = new Etel();
                    break;
                case 3:
                    d = new Kotel();
                    break;
                case 4:
                    d = new Lapat();
                    break;
                case 5:
                    d = new Sator();
                    break;
                case 6:
                    d = new PisztolyAlkatresz();
                    break;
                case 7:
                    d = new TorekenyLapat();
                    break;
                default:
                    d = null;
            }
            /*Targy hozzaadasa a tablahoz*/
            j.getMezo().hozzaadD(d);
            t.setDolog(d);
            t.setHoreteg(horeteg);
            j.getMezo().hozzaadT(t);
            kiir("tabla_hozzaadva", "output.txt");
            return true;
        }

    }

    /**
     * Kapcsolat letrehozasa ket tabla kozott.
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy ket tabla kozott a kapcsolat
     * letrejott-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean kapcsolat(String[] reszei, Jatek j) {
        if (reszei.length == 3) {
            int t1 = Integer.parseInt(reszei[1]);
            int t2 = Integer.parseInt(reszei[2]);
            /* Ellenorzi a parameteteket hogy megfeleloek-e, ha igen akkor vegrehajtja. */
            if (t1 < j.getMezo().getTablak().size() && t2 < j.getMezo().getTablak().size() && t1 >= 0 && t2 >= 0) {
                j.getMezo().getTablak().get(t1).getSzomszedok().add(j.getMezo().getTablak().get(t2));
                j.getMezo().getTablak().get(t2).getSzomszedok().add(j.getMezo().getTablak().get(t1));
                kiir("kapcsolat_letrejott_" + t1 + "_" + t2, "output.txt");
                return true;
            } else {
                kiir("kapcsolat_letrehozasa_sikertelen", "output.txt");
                return false;
            }
        }
        kiir("kapcsolat_letrehozasa_sikertelen", "output.txt");
        return false;
    }

    /**
     * Karakter hozaadasa egy megadott tablahoz.
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy a karakter felvetele
     * sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean hozzaadKarakter(String[] reszei, Jatek j) {
        if (reszei.length == 3) {
            int k = Integer.parseInt(reszei[1]);
            int t = Integer.parseInt(reszei[2]);
            /* Ellenorzi a parameteteket hogy megfeleloek-e, ha igen akkor vegrehajtja. */
            if (t < j.getMezo().getTablak().size() && t >= 0 && k >= 1 && k <= 2) {
                Karakter karakter = null;
                if (k == 1) {
                    karakter = new Eszkimo();
                }
                if (k == 2) {
                    karakter = new Sarkkutato();
                }
                j.getMezo().hozzaadK(karakter);
                j.getMezo().getTablak().get(t).getKarakterek().add(karakter);
                karakter.setTabla(j.getMezo().getTablak().get(t));
                kiir("karakter_felvetele_sikeres", "output.txt");
                return true;
            } else {
                kiir("karakter_felvetele_sikertelen", "output.txt");
                return false;
            }
        }
        kiir("karakter_felvetele_sikertelen", "output.txt");
        return false;
    }

    /**
     * Betolt egy korabban mar mentett jatekot.
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy a jatek betoltese
     * sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean betolt(String[] reszei, Jatek j) {
        /* Ellenorzi a parameteteket hogy megfeleloek-e, ha igen akkor vegrehajtja. */
        if (reszei.length == 2) {
            if (reszei[1].length() > 0) {
                Mezo m = j.mezobetolt(reszei[1]);
                if (m != null) {
                    j.setMezo(m);
                    kiir("betoltes_sikeres", "output.txt");
                    return true;
                }
            }
        }
        kiir("betoltes_sikertelen", "output.txt");
        return false;
    }

    /**
     * Jatek mentese.
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy a jatek mentese
     * sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean mentes(String[] reszei, Jatek j) {
        /* Ellenorzi a parameteteket hogy megfeleloek-e, ha igen akkor vegrehajtja. */
        if (reszei.length == 2) {
            if (reszei[1].length() > 0) {
                j.jatekmentese(reszei[1]);
                return true;
            } else {
                kiir("mentes_sikertelen", "output.txt");
                return false;
            }
        }
        kiir("mentes_sikertelen", "output.txt");
        return false;
    }

    /**
     * A program leall.
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy a program leallasa
     * sikeres volt-e terunk vissza egy ertekkel.
     */
    default boolean kilep(String[] reszei, Jatek j) {
        kiir("Program leall", "output.txt");
        System.exit(0);
        return true;
    }

    /**
     * A valasztott karakter lep a megadott mezore.
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy a lepes
     * sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean lep(String[] reszei, Jatek j) {
        if (reszei.length == 3) {
            int k = Integer.parseInt(reszei[1]);
            int i = Integer.parseInt(reszei[2]);
            /* Ellenorzi a parameteteket hogy megfeleloek-e, ha igen akkor vegrehajtja. */
            if (k < j.getMezo().getKarakterek().size() && k >= 0) {
                Tabla t = j.getMezo().getKarakterek().get(k).getTabla();
                if (i < t.getSzomszedok().size() && i >= 0) {
                    j.getMezo().getKarakterek().get(k).lep(i);
                    if (j.getMezo().getKarakterek().get(k).getHp() == 0) {
                        kiir("karakter_lepett_" + i + "_tablafelborult", "output.txt");
                        return true;
                    } else {
                        kiir("karakter_lepett_" + i, "output.txt");
                        return true;
                    }
                } else {
                    kiir("lepes_sikertelen", "output.txt");
                    return false;
                }
            } else {
                kiir("lepes_sikertelen", "output.txt");
                return false;
            }
        }
        kiir("lepes_sikertelen", "output.txt");
        return false;
    }

    /**
     * A kivalasztott karakter hasznalja a nala levo targyat.
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy a dolog hasznalata
     * sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean hasznal(String[] reszei, Jatek j) {
        if (reszei.length == 3) {
            int k = Integer.parseInt(reszei[1]);
            int i = Integer.parseInt(reszei[2]);
            /* Ellenorzi a parameteteket hogy megfeleloek-e, ha igen akkor vegrehajtja. */
            if (k < j.getMezo().getKarakterek().size() && k >= 0) {
                Tabla t = j.getMezo().getKarakterek().get(k).getTabla();
                if (j.getMezo().getKarakterek().get(k).getDolgok().get(j.getMezo().getKarakterek().get(k).getAktivdolog()).hasonlit(new Kotel())) {
                    if (i < t.getSzomszedok().size() && i >= 0) {
                        j.getMezo().getKarakterek().get(k).dolgotHasznal(i);
                        return true;
                    } else {
                        kiir("karakter_hasznal_sikertelen", "output.txt");
                        return false;
                    }
                } else {
                    if (j.getMezo().getKarakterek().get(k).dolgotHasznal(i)) j.getMezo().setNyerte(true);
                    return true;
                }
            } else {
                kiir("karakter_hasznal_sikertelen", "output.txt");
                return false;
            }
        }
        kiir("karakter_hasznal_sikertelen", "output.txt");
        return false;
    }

    /**
     * A kivalasztott karakter valt a nala levo targyak kozott.
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy az aktualis dolog
     * valtasa sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean valt(String[] reszei, Jatek j) {
        if (reszei.length == 2) {
            int k = Integer.parseInt(reszei[1]);
            /* Ellenorzi a parameteteket hogy megfeleloek-e, ha igen akkor vegrehajtja. */
            if (k < j.getMezo().getKarakterek().size() && k >= 0) {
                j.getMezo().getKarakterek().get(k).targyValtas();
                return true;
            } else {
                kiir("karakter_dologvalt_sikertelen", "output.txt");
                return false;
            }
        }
        kiir("karakter_dologvalt_sikertelen", "output.txt");
        return false;
    }

    /**
     * A kivalasztott karakter as.
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy az asas
     * sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean as(String[] reszei, Jatek j) {
        if (reszei.length == 2) {
            int k = Integer.parseInt(reszei[1]);
            /* Ellenorzi a parameteteket hogy megfeleloek-e, ha igen akkor vegrehajtja. */
            if (k < j.getMezo().getKarakterek().size() && k >= 0) {
                j.getMezo().getKarakterek().get(k).as();
                return true;
            } else {
                kiir("as_sikertelen", "output.txt");
                return false;
            }
        }
        kiir("as_sikertelen", "output.txt");
        return false;
    }

    /**
     * A kivalasztott karakter hasznalja a kepesseget.
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy a kepesseg hasznalat
     * sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean kepesseghasznal(String[] reszei, Jatek j) {
        if (reszei.length == 3) {
            int k = Integer.parseInt(reszei[1]);
            int i = Integer.parseInt(reszei[2]);
            /* Ellenorzi a parameteteket hogy megfeleloek-e, ha igen akkor vegrehajtja. */
            if (k < j.getMezo().getKarakterek().size() && k >= 0) {
                Tabla t = j.getMezo().getKarakterek().get(k).getTabla();
                if (i < t.getSzomszedok().size() && i >= 0) {
                    j.getMezo().getKarakterek().get(k).kepesseg(i);
                    return true;
                } else {
                    kiir("kepesseghasznal_sikeretelen", "output.txt");
                    return false;
                }
            } else {
                kiir("kepesseghasznal_sikeretelen", "output.txt");
                return false;
            }
        }
        kiir("kepesseghasznal_sikeretelen", "output.txt");
        return false;
    }

    /**
     * A kivalasztott karakter felveszi a tablan levo targyat.
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy a targyfelvetel
     * sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean felvesz(String[] reszei, Jatek j) {
        if (reszei.length == 2) {
            int k = Integer.parseInt(reszei[1]);
            /* Ellenorzi a parameteteket hogy megfeleloek-e, ha igen akkor vegrehajtja. */
            if (k < j.getMezo().getKarakterek().size() && k >= 0) {
                j.getMezo().getKarakterek().get(k).felvesz();
                return true;
            } else {
                kiir("karakter_dologfelvetel_sikertelen", "output.txt");
                return false;
            }
        }
        kiir("karakter_dologfelvetel_sikertelen", "output.txt");
        return false;
    }

    /**
     * Hovihar
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy a hovihar futasa
     * sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean hovihar(String[] reszei, Jatek j) {
        if (reszei.length != 2) {
            kiir("hovihar_sikertelen", "output.txt");
            return false;
        }
        int param = Integer.parseInt(reszei[1]);
        /*Ellenorzi hogy a megadott parameter alapjan a hovihar melyik valtozata fusson le.*/
        if (param == 0) {
            /*Az egesz mezon lefuto hovihar*/
            j.getMezo().hovihar(false);
            kiir("hovihar_megtortent_0", "output.txt");
            return true;
        }
        /*Ellenorzi hogy a megadott parameter alapjan a hovihar melyik valtozata fusson le.*/
        if (param == 1) {
            /*A veletlenszeru tablakon lefuto hovihar*/
            j.getMezo().hovihar(true);
            kiir("hovihar_megtortent_1 ", "output.txt");
            return true;
        }
        /*Hibas parameter eseten sikertelen*/
        kiir("hovihar_sikertelen", "output.txt");
        return false;
    }

    /**
     * Npc hozzaadasa a kivalasztott tablahoz
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy az Npc hozzaadasa a tablahoz
     * sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean hozzaadNpc(String[] reszei, Jatek j) {
        if (reszei.length != 3) {
            kiir("npc_hozzaadasa_sikertelen", "output.txt");
            return false;
        }
        /*Npc tipusa*/
        int tip = Integer.parseInt(reszei[1]);
        /*Tabla indexe*/
        int tab = Integer.parseInt(reszei[2]);
        if (tip != 1) {
            /*Hibas parameter eseten sikertelen*/
            kiir("npc_hozzaadasa_sikertelen", "output.txt");
            return false;
        }
        /*Maximalis tabla index*/
        int indexmax = j.getMezo().getTablak().size() - 1;
        /*Ellenorzi hogy a parameter valoban egy tabla indexe-e*/
        if (tab > indexmax || tab < 0) {
            kiir("npc_hozzaadasa_sikertelen", "output.txt");
            return false;
        }
        Jegesmedve jegesm = new Jegesmedve();
        /*Jegesmedve npck listajahoz adasa*/
        j.getMezo().getNpck().add(jegesm);
        /*Jegesmedve tablahoz adasa*/
        j.getMezo().getTablak().get(tab).hozzaadNpc(jegesm);
        /*Jegesmedve tablajanak beallitasa*/
        jegesm.setTabla(j.getMezo().getTablak().get(tab));
        kiir("npc_hozzaadasa_sikeres", "output.txt");
        return true;
    }

    /**
     * Kivalasztott npc leptetese
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy az Npc lepes
     * sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean lepNpc(String[] reszei, Jatek j) {
        if (reszei.length == 3) {
            /*Npc indexe*/
            int npc = Integer.parseInt(reszei[1]);
            /*Lepes iranya*/
            int irany = Integer.parseInt(reszei[2]);
            /*Maximalis npc index*/
            int npcmax = j.getMezo().getNpck().size();
            /*Ellenorzi hogy a parameter valoban egy npc indexe-e*/
            if (npc < npcmax && npc >= 0) {
                int iranymax = j.getMezo().getNpck().get(npc).getTabla().getSzomszedok().size();
                /*Ellenorzi hogy a megadott irany valoban letezik*/
                if (irany < iranymax && irany >= 0) {
                    j.getMezo().getNpck().get(npc).cselekszik(irany);
                    return true;
                }
                kiir("lepes_sikertelen", "output.txt");
                return false;
            }
        } else {
            kiir("lepes_sikertelen", "output.txt");
            return false;
        }
        kiir("lepes_sikertelen", "output.txt");
        return false;
    }

    /**
     * Epitmeny hozzaadasa a kivalasztott tablahoz
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy az Epitmeny hozzaadasa a tablahoz
     * sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean hozzaadEpitmeny(String[] reszei, Jatek j) {
        if (reszei.length != 3) {
            kiir("epitmeny_hozzaadasa_sikertelen", "output.txt");
            return false;
        }
        int tipus = Integer.parseInt(reszei[1]);
        int tabla = Integer.parseInt(reszei[2]);
        /*Ellenorzi hogy a megadott ertek valoban letezo epitmeny tipus*/
        if (tipus > 1 || tipus < 0) {
            kiir("epitmeny_hozzaadasa_sikertelen", "output.txt");
            return false;
        }
        int tablamax = j.getMezo().getTablak().size() - 1;
        /*Ellenorzi hogy a tabla valoban letezik-e*/
        if (tabla > tablamax || tabla < 0) {
            kiir("epitmeny_hozzaadasa_sikertelen", "output.txt");
            return false;
        }
        /*Epitmeny tipusa sator*/
        if (tipus == 0) {
            Sator s = new Sator();
            j.getMezo().getTablak().get(tabla).setEpitmeny(s);
            s.setFelallitva(true);
            s.setTaba(j.getMezo().getTablak().get(tabla));
            j.getMezo().getEpitmenyek().add(s);
            kiir("epitmeny_hozzaadasa_sikeres", "output.txt");
            return true;
        }
        /*Epitmeny tipusa iglu*/
        if (tipus == 1) {
            j.getMezo().getTablak().get(tabla).igluEpit();
            kiir("epitmeny_hozzaadasa_sikeres", "output.txt");
            return true;
        }
        kiir("epitmeny_hozzaadasa_sikertelen", "output.txt");
        return false;
    }

    /**
     * Kivalasztott epitmeny frissitese
     *
     * @param reszei A kiadott parancs reszeit tartalmazza.
     * @param j      A jatek maga.
     * @return boolean Attol fuggoen, hogy az epitmeny frissitese
     * sikeres volt-e vagy sem terunk vissza egy ertekkel.
     */
    default boolean epitmenyFrissit(String[] reszei, Jatek j) {
        if (reszei.length == 2) {
            int index = Integer.parseInt(reszei[1]);
            /*Ellenorzi hogy a megadott indexu epitmeny letezik-e*/
            if (index < j.getMezo().getEpitmenyek().size() && index >= 0) {
                int karakterek = j.getMezo().getKarakterek().size();
                j.getMezo().getEpitmenyek().get(index).allapotFrissit(karakterek);
                return true;
            }
        } else {
            kiir("epitmeny_frissites_sikertelen", "output.txt");
            return false;
        }
        kiir("epitmeny_frissites_sikertelen", "output.txt");
        return false;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Parancsok beolvasas faljbol.
     *
     * @param filename A beolvasni kivant fajl neve.
     * @param j        A jatek maga.
     * @return boolean Sikeres volt e az összes parancs feldoldozása / beolvasása
     */
    default boolean fparancsokbeolvas(String filename, Jatek j) {

        /* Eddig meglevo adatok torlese. */
        fparancsok.clear();
        File file = new File(filename);
        Scanner sc;

        /* File megnyitasa, scannelese. */
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            error("File megnyitasa sikertelen: " + e);
            return false;
        }

        /* Parancsok beolvasas, es eltarolas. */
        while (sc.hasNext()) {
            String line = sc.nextLine();
            fparancsok.add(line);
        }
        int i = 0;
        boolean mehet = true;
        while (mehet && i < fparancsok.size()) {
            if (!parancsfeldolgozas(fparancsok.get(i), j))
                return false;
            i++;
        }
        return true;
    }

    /**
     * Hibak kiirasa standard inputra, es errorLog fileba.
     *
     * @param s A kiirando szoveg.
     */
    default void error(String s) {
        String e = "Error: -> " + s;
        /* String kiirasa az errorLog file-ba. */
        kiir(e, "errorLog.txt");
    }

    /**
     * Tortenesek kiirasa, es log fileba.
     *
     * @param s A kiirando szoveg.
     */
    default void log(String s) {
        kiir(s, "log.txt");
    }

    /**
     * Kiirja a tortent dolgot standard inputra, es kiiratja fileba, ha kapott file nevet.
     *
     * @param filename Annak a fajlnak a neve ahova ki szeretnenk irni.
     * @param s        A kiirando szoveg.
     */
    default void kiir(String s, String filename) {
        /* Ha a file neve nem egyezik a log.txt-vel, akkor a standart outputra irja ki. */
        //if(filename != "log.txt")
        //    System.out.println(s);
        /* Ha van megadva file nev, akkor file-ba valo kiiras is tortenik. */
        if (filename != null)
            fkiir(s, filename);
    }

    /**
     * Kapott stringet kiirja a kapott nevu fileba.
     *
     * @param filename Annak a fajlnak a neve ahova ki szeretnenk irni.
     * @param s        A kiirando szoveg.
     */
    default void fkiir(String s, String filename) {

        FileWriter fileWriter = null;
        try {
            /* A parameterben megadott file nevu fajlba ir */
            fileWriter = new FileWriter(filename, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        /* A parameterben megadott String-et a parameterben megadott file-ba irja. */
        printWriter.println(s);
        printWriter.close();
    }

    /**
     * Uj fileokat hoz letre a mostani futtatashoz (ha volt elozo tartalom torli).
     */
    static void freset() {
        FileWriter fileWriter = null;
        String[] filenames = {"output.txt", "log.txt", "errorLog.txt", "defaultGame.txt"};
        for (int i = 0; i < 4; i++) {
            try {
                fileWriter = new FileWriter(filenames[i], false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter printWriter = new PrintWriter(fileWriter);
            /* A file tartalmat uresre allitja. */
            printWriter.print("");
            printWriter.close();
        }
    }

    /**
     * Automatikusan generál egy n*n es pályát (jégmezőt)
     * n*n táblát fog tartalmazni véletlenszerűen, de játszhatóan.
     * @param n Az a szám ami megmondja hányszor hányas pályát szeretnénk generáltatni.
     */
    static void palyagen(int n) {

        Random r = new Random();

        String p1 = "hozzaadTabla";

        FileWriter fileWriter = null;
        try {
            /* A parameterben megadott file nevu fajlba ir */
            fileWriter = new FileWriter("defaultGame.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("ujJatek");
        int[] tipus = new int[n*n];
        int[] stabil = new int[n*n];
        int[] dolog = new int[n*n];
        int[] horeteg = new int[n*n];
        int pisztoly = 0;

        //veletlenszeruseg
        for(int i = 0; i < n*n; i++) {

            tipus[i] = r.nextInt(3) + 1;
            if(tipus[i] == 3)
                stabil[i] = 0;
            else if(tipus[i] == 1)
                stabil[i] = 10;
            else stabil[i] = r.nextInt(9)+1;
            dolog[i] = r.nextInt(8);
            if (dolog[i] == 6) dolog[i] = 2;
            horeteg[i] = r.nextInt(3);
        }
        // 2 kezdoreszen stabil tablak legyenek
        tipus[0] = 1;
        tipus[1] = 1;
        tipus[n] = 1;
        tipus[n*n-2] = 1;
        tipus[n*n-1] = 1;
        tipus[n*n-1-n] = 1;
        int max = 0;
        while(pisztoly < 3 && max < 100) {
            max++;
            int t = r.nextInt(n*n);
            if(tipus[t] != 3 && dolog[t] != 6){
                dolog[t] = 6;
                horeteg[t] = 2;
                stabil[t] = 3;
                pisztoly++;
            }
        }
        //ne legyen sziget, sok viz
        for(int i = 0; i < n*n; i++){

            if(i+1 < n*n && i-1 >-1 && tipus[i+1] == 3 && tipus[i-1] == 3){
                tipus[i+1] = 1;
                stabil[i+1] = 10;
            }
            if(i+n < n*n && i-n >-1 && tipus[i+n] == 3 && tipus[i-n] == 3){
                tipus[i+n] = 1;
                stabil[i+n] = 10;
            }
            if(i+n+1 < n*n && i-n-1 >-1 && tipus[i+n+1] == 3 && tipus[i-n-1] == 3){
                tipus[i+n+1] = 1;
                stabil[i+n+1] = 10;
            }
            if(i-n+1 > -1 && i+n-1 < n*n && tipus[i-n+1] == 3 && tipus[i+n-1] == 3){
                tipus[i-n+1] = 1;
                stabil[i-n+1] = 10;
            }
            if(i+n < n*n && i+1 <n*n && tipus[i+n] == 3 && tipus[i+1] == 3){
                tipus[i+n] = 1;
                stabil[i+n] = 10;
            }
            if(i-n > -1 && i-1 > -1 && tipus[i-n] == 3 && tipus[i-1] == 3){
                tipus[i-n] = 1;
                stabil[i-n] = 10;
            }
            if(i+n < n*n && i-1 > -1 && tipus[i+n] == 3 && tipus[i-1] == 3){
                tipus[i+n] = 1;
                stabil[i+n] = 10;
            }
            if(i-n > -1 && i+1 <n*n && tipus[i-n] == 3 && tipus[i+1] == 3){
                tipus[i-n] = 1;
                stabil[i-n] = 10;
            }

        }
        // ha viz akkor ne legyen dolog rajta
        String p2;
        for(int i = 0; i < n*n; i++) {
            if(tipus[i] == 3) dolog[i] = 0;
            p2 = p1 + " " + tipus[i] + " " + stabil[i] + " " + dolog[i] + " " + horeteg[i];
            printWriter.println(p2);
        }
        // kapcsolatok letrehozasa
        String p3 = "";
        for(int i = 0; i < n*n; i++){
            if(i + 1 < n*n) {
                if(i%n != n-1) {
                    p3 = "kapcsolat " + i + " " + (i + 1);
                    printWriter.println(p3);
                }
            }
            if(i + n < n*n) {
                p3 = "kapcsolat " + i + " " + (i+n);
                printWriter.println(p3);
            }
        }
        printWriter.close();
    }
}