# Parrot Mambo Drone Programlama

    Bu projede Parrot Mambo Drone kullanılarak çocuklara temel algoritma becerisi kazandırılmak istenmiştir.
  Uygulama, bulmacaların çözülmesi ile ortaya çıkan dizaynın drone tarafından otomatik bir şekilde takip edilmesi şeklinde çalışıyor.
  
  Projede işleyiş drone ile bağlantı kurulup, 3 adet bulmacanın çözülmesi üzerinedir. Bu bulmacalar;
  
    1.Bulmaca (PuzzleActivity - Resimlerin kaydırılarak doğru tasarımın bulunması)
    2.Bulmaca (DragNDrop - Ekrandaki yolun uygun tuşlara basılarak ilerlenmesi)
    3.Bulmaca (GridActivity - Ekrandaki yolda uygun yön ve birim değerlerinin girilmesi)
    
  Geliştirme sürecinde https://github.com/Parrot-Developers/ARSDK3 ve http://developer.parrot.com/docs/reference/all kullanıldı
  
  MiniDrone controller üzerinde gerekli eklemeler yapılarak takla atma fonksiyonu eklendi ve çalışır hale getirildi.
  
  Bilinen hatalar: 
  
      - 3. bulmaca sonu drone bağlantısı kesilmek istenirken bir hata veriyor fakar programın çalışması engellenmiyor.
      
      - 2. bulmaca relative tasarlandığı için kullanılan cihaza göre tasarımı düzenlemek gerekiyor. Ayrıca 1. bulmacada da kullanılan cihaza göre 
      ayarlama yapılması görselliği arttıracaktır.
