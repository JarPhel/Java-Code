import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.lang.String;
public class Main {
    private static final String Buyuk_Harf = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String Kucuk_Harf = "abcdefghijklmnopqrstuvwxyz";
    private static final String Sayilar = "0123456789";
    private static final String Ozel_Karakter = "_?@$-^&+=<!#>%*(){}|";

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int secim = 0;
        int sec2  = 0;
        String ad, soyad, kullaniciad, sifre, passsecenekbelirle;

        int MinUzunluk =12;
        int MaxUzunluk =24;
        String pass = RanSifreOlustur(MinUzunluk,MaxUzunluk);

        while (secim != 2) {
            System.out.println("Yapmak istediğin işlemi seç :");
            System.out.println("1 -- KAYIT OL");
            System.out.println("2 -- GİRİŞ YAP");
            System.out.println("0 -- ÇIKIŞ");

            secim = scanner.nextInt();

            if (secim == 0) {
                System.out.println("Çıkılıyor...");
                break;
            }
            else if (secim == 1) {
                System.out.println("I. Seçenek Seçildi ...");
                System.out.println("Lütfen Adını Gir :");
                ad = scanner.next();
                System.out.println("Soyadını Gir :");
                soyad = scanner.next();
                System.out.println("Kullanıcı Adı gir:");
                kullaniciad = scanner.next();
                System.out.println("Şifrenin Otomatik verilmesini istiyorsan 7 kendin belirlemek istiyorsan 9 tuşla");
                sec2 = scanner.nextInt();
                if (sec2 == 7)
                {
                    System.out.println("Oluşturulan şifren : "+ pass);
                    kayitOl(kullaniciad,pass);

                } else if (sec2 == 9)
                {
                    System.out.println("Parolanı Gir");
                    sifre = scanner.next();
                    System.out.println("Kaydın başarıyla tamamlandı" + " " + ad + " " + soyad + " ");
                    // Kullanıcı adı ve şifreyi kaydet
                    kayitOl(kullaniciad, sifre);
                }
                else
                {
                    System.out.println("Herhangi bir tuşlama yapmadın veya yanlış tuşladın tekrar dene lütfen.");
                }
            }
            else if (secim == 2) {
                boolean girisBasarili = false;
                do {
                    System.out.println("II. Seçenek Seçildi ...");
                    System.out.println("Kullanıcı Adını Gir :");
                    String loginkullaniciad = scanner.next();
                    System.out.println("Şifreni Gir :");
                    String loginsifre = scanner.next();
                    if (onlineol(loginkullaniciad, loginsifre)) {
                        System.out.println("Giriş başarılı. Hoş geldiniz, ");
                        System.out.println("-----------//----------" + loginkullaniciad + "-----------//----------");
                        System.out.println("1-Password generator");
                        int secim2 = scanner.nextInt();

                        switch (secim2) {
                            case 1:
                                System.out.println("Password generator activited");

                                System.out.println("Oluşturulan şifren : "+ pass);
                                break;
                            default:
                                System.out.println("Yanlış seçenek. Lütfen 1-2-3-4 seçin.");
                        }
                        girisBasarili = true;
                    } else {
                        System.out.println("Giriş başarısız. Geçersiz kullanıcı adı veya şifre.");
                        System.out.println("Tekrar yönlendiriliyorsun...");
                    }
                } while (!girisBasarili);
            } else {
                System.out.println("Herhangi bir seçim olmadı veya yanlış tuşlama yaptın tekrar dene.");
            }
        }
    }

    // Kullanıcı adı ve şifre kontrolü
    public static boolean onlineol(String loginkullaniciad, String loginsifre) {
        try {
            File file = new File("kullanicilar.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String satir = scanner.nextLine();
                String[] parcalar = satir.split(" ");
                if (parcalar.length == 2 && parcalar[0].equals(loginkullaniciad) && parcalar[1].equals(loginsifre)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kullanıcı adı ve şifreyi kaydet
    public static void kayitOl(String loginkullaniciad, String loginsifre) {
        try {
            FileWriter fileWriter = new FileWriter("kullanicilar.txt", true);
            fileWriter.write(loginkullaniciad + " " + loginsifre + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String RanSifreOlustur(int MinUzunluk, int MaxUzunluk) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis()); // Başlangıç tohumunu zaman ile ayarla
        StringBuilder pass = new StringBuilder();
        String TumKarakterler = Buyuk_Harf + Kucuk_Harf + Sayilar + Ozel_Karakter;
        int SifreUzunlugu = random.nextInt(MaxUzunluk - MinUzunluk + 1) + MinUzunluk;

        for (int i = 0; i <SifreUzunlugu; i++)
    {
        int RastgeleIndex = random.nextInt(TumKarakterler.length());
        char RastgeleKarakter = TumKarakterler.charAt(RastgeleIndex);
        pass.append(RastgeleKarakter);
    }
    return pass.toString();
    }
}
