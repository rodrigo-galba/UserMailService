# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

Vagrant.require_version ">= 1.5.0"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  config.vm.box = "ubuntu/xenial64"
  config.vm.synced_folder ".", "/vagrant", type: 'nfs'
  config.vm.synced_folder ".", "/home/ubuntu/project", type: 'nfs'

  config.vm.provider :virtualbox do |vb|
    vb.gui = false
    vb.customize [
      "modifyvm", :id,
      "--memory", "1024",
      # set available CPU's count
      "--cpus", 2
    ]
  end

  config.vm.network :forwarded_port, guest: 6379, host: 6379
  config.vm.network :forwarded_port, guest: 1080, host: 1080
  config.vm.network :forwarded_port, guest: 1025, host: 1025
  config.vm.network :forwarded_port, guest: 5432, host: 5432
  config.vm.network :forwarded_port, guest: 5672, host: 5672
  config.vm.network :forwarded_port, guest: 15672, host: 15672
  config.vm.network :forwarded_port, guest: 8080, host: 8080
  config.vm.network :forwarded_port, guest: 8888, host: 8888

  config.vm.define :dev, primary: true do |config|
    config.vm.network :private_network, ip: "192.168.3.5"
    config.vm.provision :ansible_local do |resume|
      resume.playbook = "playbook.yml"
      resume.verbose = false
      # resume.limit = "@/vagrant/playbook.retry"
    end
  end

end